class Bst
  attr_reader :data, :left, :right

  def initialize(number)
    @data = number
    @left = nil
    @right = nil
  end

  def insert(number)
    number <= data ? add_to_left(number) : add_to_right(number)
  end

  def add_to_left(number)
    @left.nil? ? @left = Bst.new(number) : @left.insert(number)
  end

  def add_to_right(number)
    @right.nil? ? @right = Bst.new(number) : @right.insert(number)
  end

  def each
    all.each { |e| yield e }
  end

  def all
    result = []
    result += left.all if left
    result += [data]
    result += right.all if right
    result
  end

end
