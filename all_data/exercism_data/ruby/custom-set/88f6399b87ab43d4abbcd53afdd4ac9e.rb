class CustomSet
  include Comparable

  def initialize(data = [])
    empty
    Array(data).each &method(:insert)
  end

  def empty
    tap { @store = Hash.new }
  end

  def ==(other)
    store == other.store
  end

  def difference(other)
    new(to_a - other.to_a)
  end

  def delete(e)
    tap { store.delete(e) }
  end

  def to_a
    store.keys
  end

  def disjoint?(other)
    intersection(other).empty?
  end

  def intersection(other)
    new(to_a & other.to_a)
  end

  def empty?
    store.empty?
  end

  def size
    store.size
  end

  def put(e)
    tap { insert(e) }
  end

  def member?(e)
    store.has_key?(e)
  end

  def union(other)
    new(to_a + other.to_a)
  end

  def subset?(other)
    intersection(other) == other
  end

  def to_list
    to_a
  end

  protected

  attr_reader :store

  private

  def insert(e)
    store[e] = 1
  end

  def new(data)
    CustomSet.new(data)
  end
end
