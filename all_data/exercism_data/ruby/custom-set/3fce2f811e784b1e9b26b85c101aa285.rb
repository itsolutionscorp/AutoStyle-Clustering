class CustomSet
  def initialize(data = [])
    @set = parse_data(data)
  end

  def to_a
    @set.keys
  end

  def size
    @set.count
  end

  def empty?
    @set.empty?
  end

  def empty
    initialize

    self
  end

  def delete(datum)
    CustomSet.new(to_a - [datum])
  end

  def put(datum)
    CustomSet.new(to_a << datum)
  end

  def difference(other)
    CustomSet.new(to_a - other.to_a)
  end

  def intersection(other)
    CustomSet.new(to_a & other.to_a)
  end

  def disjoint?(other)
    intersection(other).empty?
  end

  def member?(datum)
    @set.key? datum
  end

  def subset?(other)
    other.difference(self).empty?
  end

  def union(other)
    CustomSet.new(to_a | other.to_a)
  end

  def ==(other)
    to_a.sort == other.to_a.sort
  end

  private

  def parse_data(data)
    {}.tap do |set|
      data.to_a.each { |datum| set[datum] = true }
    end
  end
end
