package org.processor.proto.repository;

import org.processor.proto.model.GuestProtos;

public interface GuestRepository {

    GuestProtos.Guest findById(int id);
}
