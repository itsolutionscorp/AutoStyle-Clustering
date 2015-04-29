# bst.rb
class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
  end

  def nil.all
    []
  end

  def insert(value)
    direction = value > data ? :right : :left
    node = send(direction)
    if node.nil?
      instance_variable_set "@#{direction}", Bst.new(value)
    else
      node.insert(value)
    end
  end

  def all
    left.all + [data] + right.all
  end

  def each(&block)
    all.each(&block)
  end
end
