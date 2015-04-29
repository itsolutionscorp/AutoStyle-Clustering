class CustomSet

  attr_reader :data

  def initialize(input = [])
    @data = parse(Array(input))
  end

  def put(item)
    @data.push item
    @data.sort!
    @data.uniq!
    self
  end

  def ==(other)
    data == other.data
  end

  def empty
    @data.clear
    self
  end

  def union other_set
    other_set.data.each do |item|
      put item
    end
    self
  end

  def to_a
    @data
  end

  def size
    @data.size
  end

  def subset?(other_set)
    other_set.data.all? { |n| include?(n) }
  end

  def disjoint?(other_set)
    not @data.any? { |n| other_set.include?(n) }
  end

  def include?(item)
    @data.include?(item) && item.class == @data.find {|n| n == item}.class
  end

  def member?(item)
    include?(item)
  end

  def difference(other_set)
    @data.reject! { |n| other_set.include?(n) }
    self
  end

  def intersection(other_set)
    @data.select! { |n| other_set.include?(n) }
    self
  end

  def delete(value)
    @data.delete(value) if @data.any? { |n| n.class == value.class }
    self
  end

  private

  def parse(input)
    input.uniq.sort
  end

end
