class CustomSet
  attr_reader :data

  def initialize(data = [])
    set_data(data)
  end

  def ==(other)
    data == other.data
  end

  def put(datum)
    @data[datum] = nil
    self
  end

  def delete(datum)
    @data.delete(datum)
    self
  end

  def to_a
    data.keys
  end

  def size
    data.length
  end

  def difference(other)
    self.class.new(self.to_a - other.to_a)
  end

  def intersection(other)
    self_only = difference(other)
    other_only = other.difference(self)
    union(other).difference(self_only).difference(other_only)
  end

  def union(other)
    self.class.new(to_a + other.to_a)
  end

  def subset?(other)
    other.intersection(self) == other
  end

  def member?(datum)
    data.member?(datum)
  end

  def disjoint?(other)
    difference(other) == self
  end

  def empty
    set_data([])
    self
  end

  private

  def set_data(data)
    @data = Hash[data.map { |datum| [datum, nil] }]
  end
end
