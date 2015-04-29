class CustomSet
  include Enumerable

  def initialize(enum = [])
    @hash = {}

    enum.each { |obj| put(obj) }
  end

  def each(&block)
    hash.each_key(&block)
  end

  def ==(other)
    hash == other.hash
  end

  def member?(obj)
    hash.key?(obj)
  end

  def empty
    hash.clear
    self
  end

  def size
    hash.size
  end

  def to_a
    hash.keys
  end

  def put(obj)
    hash[obj] = true
    self
  end

  def delete(obj)
    hash.delete(obj)
    self
  end

  def union(other)
    dup.tap do |set|
      other.each { |obj| set.put(obj) }
    end
  end

  def intersection(other)
    self.class.new.tap do |set|
      other.each { |obj| set.put(obj) if member?(obj) }
    end
  end

  def difference(other)
    dup.tap do |set|
      other.each { |obj| set.delete(obj) }
    end
  end

  def subset?(other)
    other.all? { |obj| member?(obj) }
  end

  def disjoint?(other)
    other.none? { |obj| member?(obj) }
  end

  protected

  attr_reader :hash
end
