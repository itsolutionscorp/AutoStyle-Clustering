class CustomSet
  def initialize(list = [])
    empty
    list.each { |value| put(value) }
  end

  def put(value)
    internal_hash[value] = nil
    self
  end

  def delete(value)
    internal_hash.delete(value)
    self
  end

  def empty
    @internal_hash = {}
    self
  end

  def member?(value)
    internal_hash.has_key?(value)
  end

  def to_list
    internal_hash.keys
  end

  def size
    internal_hash.size
  end

  def ==(other)
    internal_hash == other.internal_hash
  end

  def difference(other)
    self.class.new(to_list - other.to_list)
  end

  def intersection(other)
    self.class.new(to_list & other.to_list)
  end

  def union(other)
    self.class.new(to_list | other.to_list)
  end

  def disjoint?(other)
    intersection(other).empty?
  end

  def subset?(other)
    other.difference(self).empty?
  end

  protected

  def empty?
    internal_hash.empty?
  end

  attr_reader :internal_hash
end
