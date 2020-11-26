package com.mycompany.webapp.storage;

import com.mycompany.webapp.model.Resume;
import sun.security.krb5.internal.crypto.RsaMd5CksumType;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(this.storage, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = this.getIndex(r);
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("This resume not exists in storage!");
        }
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Storage is full");
            return;
        }
        if (this.getIndex(r) == -1) {
            this.storage[size] = r;
            size++;
        } else {
            System.out.println("This resume already exists in storage");
        }
    }

    public Resume get(String uuid) {
        Resume r = new Resume();
        r.setUuid(uuid);
        int index = getIndex(r);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Resume with uuid = " + uuid + " not exists in storage");
        }
        return null;
    }

    public void delete(String uuid) {
        Resume r = new Resume();
        r.setUuid(uuid);
        int index = getIndex(r);
        if (index == 1) {
            System.out.println("Resume with uuid = " + uuid + " not exists in storage");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(this.storage, size());
    }

    public int size() {
        return size;
    }

    public int getIndex(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(r)) {
                return i;
            }
        }
        return -1;
    }
}
