package cz.zcu.kiv.nlp.ir.trec.dataStructures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by msip on 5/4/17.
 */

public class PostingsList implements Serializable{

    private List<Posting> postings;

    public PostingsList() {
        this.postings = new ArrayList<>();
    }

    public PostingsList(final PostingsList p) {
        this.postings = new ArrayList<>(p.getPostings());
    }

    public void addPosting(final Posting posting) {
        postings.add(posting);
    }

    public List<Posting> getPostings() {
        return this.postings;
    }

    public int size() {
        return this.postings.size();
    }

    public static PostingsList orOperation(final PostingsList first, final PostingsList second) {
        if(first == null && second == null) {
            return null;
        }else if(first == null) {
            return new PostingsList(second);
        } else if(second == null){
            return new PostingsList(first);
        } else{
            return first;
        }
    }

    public static PostingsList andOperation(final PostingsList first, final PostingsList second){
        if(first == null && second == null) {
            return null;
        }else if(first == null) {
            return new PostingsList(second);
        } else if(second == null){
            return new PostingsList(first);
        } else{

            PostingsList result = new PostingsList();

            List<Posting> firstList = first.getPostings();
            List<Posting> secondList = second.getPostings();

            int i1 = 0, i2 = 0;
            while(i1 < firstList.size() && i2 < secondList.size()) {
                if(firstList.get(i1).getDocumentId() == secondList.get(i2).getDocumentId()){
                    result.addPosting(firstList.get(i1));
                    i1++; i2++;
                }else if(firstList.get(i1).getDocumentId() < secondList.get(i2).getDocumentId()) {
                    i1++;
                }else {
                    i2++;
                }
            }
            return result;
        }
    }



}
