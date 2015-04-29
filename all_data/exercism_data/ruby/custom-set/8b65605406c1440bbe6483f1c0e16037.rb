class CustomSet
  include Comparable

  def initialize(array = [])
    @hash = array.zip([]).to_h
  end

  def <=>(other)
    hash <=> other.hash
  end

  def put(entry)
    tap { hash.store entry, nil }
  end

  def delete(entry)
    tap { hash.delete entry }
  end

  def empty
    tap { hash.clear }
  end

  def difference(other)
    tap { hash.delete_if { |k| other.member? k } }
  end

  def intersection(other)
    tap { hash.keep_if { |k| other.member? k } }
  end

  def union(other)
    tap { hash.merge! other.hash }
  end

  def disjoint?(other)
    entries.none? { |k| other.member? k }
  end

  def subset?(other)
    other.entries.all? { |k| member? k }
  end

  def member?(entry)
    hash.key?(entry)
  end

  def size
    hash.size
  end

  def entries
    hash.keys
  end
  alias_method :to_a, :entries

  protected

  attr_reader :hash
end
