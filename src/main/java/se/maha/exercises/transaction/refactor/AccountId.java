package se.maha.exercises.transaction.refactor;

public record AccountId(String id) {

    public AccountId {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("AccountId cannot be null or empty");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other != null && this.getClass() != other.getClass()) return false;

        AccountId accountId = (AccountId) other;
        return this.id.equals(accountId.id);
    }

}