class CustomSet
  include Comparable
  attr_reader :set

  def initialize(array=[])
    @set = array.to_a.uniq
  end

  def <=>(other)
    set.sort <=> other.set.sort
  end

  def delete(element)
    set.delete(element) if member?(element)
    self
  end

  def difference(other)
    diff_set = set.delete_if { |i| other.member?(i) }
    self.class.new(diff_set)
  end

  def disjoint?(other)
    set.none? { |i| other.member?(i) }
  end

  def empty
    self.class.new()  # other solution: @set = []; self
  end

  def intersection(other)
    intersects = set.keep_if { |i| other.set.any? { |j| i.eql? j } }
    self.class.new(intersects)
  end

  def member?(element)
    set.any? { |i| i.eql? element }
  end

  def put(element)
    self.class.new(set << element)  #set << element unless member?(element); self
  end

  def size
    set.size
  end

  def to_a
    set
  end

  def subset?(other)
    other.set.all? { |i| member?(i) }
  end

  def union(other)
    self.class.new(set + other.set)
  end
end
