CREATE TABLE loans (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    member_id UUID NOT NULL,
    book_id UUID NOT NULL,
    loan_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(20) NOT NULL,
    notes TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_loans_member
        FOREIGN KEY (member_id) REFERENCES members(id),

    CONSTRAINT fk_loans_book
        FOREIGN KEY (book_id) REFERENCES books(id)
);