class CustomSet
  attr_reader :items

  def initialize(items=[])
    @items = items.to_a.sort
  end

  def ==(other_set)
    @items == other_set.items
  end

  def delete(item)
    @items.reject!{|e| e.equal?(item)}
    self
  end

  def difference(other_set)
    CustomSet.new(@items - other_set.items)
  end

  def disjoint?(other_set)
    !@items.any?{|e| other_set.member?(e)}
  end

  def empty
    @items = []
    self
  end

  def intersection(other_set)
    CustomSet.new(@items.select{|e| other_set.member?(e)})
  end

  def member?(item)
    @items.any?{|e| e.equal?(item)}
  end

  def put(item)
    return self if member?(item)
    @items << item
    @items.sort!
    self
  end

  def size
    @items.uniq.size
  end

  def subset?(other_set)
    return true if other_set.items.empty?
    @items.each_cons(other_set.items.size).any?{|sub| sub.eql?(other_set.items)}
  end

  def to_list
    @items.uniq
  end

  def union(other_set)
    CustomSet.new(@items + other_set.items)
  end
end
