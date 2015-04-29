# bst.rb
class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
  end

  # hmm...
  def nil.insert(value)
    Bst.new(value)
  end

  def nil.all
    []
  end

  def grow(direction, value)
    node = send("#{direction}")
    subtree = node.insert(value)
    instance_variable_set "@#{direction}", subtree if node.nil?
  end

  def insert(value)
    direction = value > data ? :right : :left
    grow(direction, value)
  end

  def all
    left.all + [data] + right.all
  end

  def each(&block)
    all.each(&block)
  end
end
