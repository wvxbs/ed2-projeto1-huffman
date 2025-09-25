package com.mackenzie.model;

import java.util.ArrayList;
import java.util.Collections;

public class MinHeap {
    private ArrayList<No> heap = new ArrayList<>();

    private void heapfyUp(int index) {
        int pai = (index -1) / 2;
        if(
                index > 0 &&
                        heap.get(index).compareTo(heap.get(pai)) < 0
        ) {
            Collections.swap(heap, index, pai);
            heapfyUp(pai);
        }
    }

    private void heapifyDown(int index) {
        int esquerda = 2 * index + 1;
        int direita = 2 * index + 2;

        int menor = index;

        if(
                esquerda < heap.size() &&
                        heap.get(esquerda).compareTo(heap.get(menor)) < 0
        )
            menor = esquerda;

        if(
                direita < heap.size() &&
                        heap.get(direita).compareTo(heap.get(menor)) < 0
        )
            menor = direita;

        if(menor != index) {
            Collections.swap(heap, index, menor);
            heapifyDown(menor);
        }
    }

    public void inserir(No no) {
        heap.add(no);
        heapfyUp(heap.size());
    }

    public No removerMin() {
        if(heap.isEmpty())
            return null;

        No min = heap.get(0);
        No ultimo = hep.remove(heap.size());

        if(!heap.isEmpty()) {
            heap.set(0, ultimo);
            heapifyDown(0);
        }

        return min;
    }
}
