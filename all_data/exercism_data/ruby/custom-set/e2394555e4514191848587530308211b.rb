class CustomSet

  def initialize collection = []
    @set = collection.to_a.uniq.sort
  end

  def == other
    other.set == set
  end

  def difference other
    self.class.new(set - other.set)
  end

  def intersection other
    self.class.new(set & other.set)
  end

  def union other
    self.class.new(set | other.set)
  end

  def disjoint? other
    (set & other.set).empty?
  end

  def member? elem
    elem.integer? && set.include?(elem)
  end

  def subset? other
    set & other.set == other.set
  end

  def put elem
    set << elem
    @set = set.uniq.sort
    self
  end

  def delete n
    set.delete(n) if n.integer?
    self
  end

  def size
    set.size
  end

  def to_list
    set
  end

  def empty
    set.clear
    self
  end

  protected
  attr_reader :set
end
