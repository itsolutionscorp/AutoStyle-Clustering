class CustomSet
  def initialize(arr=[])
    if arr.instance_of?(Range)
      @arr = arr.to_a
    else
      @arr = arr.uniq
    end
  end

  def to_a
    @arr.dup
  end

  def size
    @arr.size
  end

  def empty
    @arr.clear
    self
  end

  def member?(value)
    @arr.any? { |i| i.eql?(value) }
  end

  def delete(value)
    @arr.each { |i| @arr.delete(i) if i.eql?(value) }
    self
  end

  def ==(other)
    # [1, 2] == [1, 2.0] => true
    # [1, 2].eql?([1, 2.0]) => false
    to_a.sort.eql?(other.to_a.sort)
  end

  def difference(other)
    diff = @arr.reject { |i| other.member?(i) }
    CustomSet.new(diff)
  end

  def intersection(other)
    int = @arr.select { |i| other.member?(i) }
    CustomSet.new(int)
  end

  def disjoint?(other)
    difference(other) == self
  end

  def put(value)
    @arr.push(value) unless member?(value)
    self
  end

  def subset?(other)
    other.to_a.all? { |i| member?(i) }
  end

  def union(other)
    CustomSet.new(to_a + other.to_a)
  end
end
