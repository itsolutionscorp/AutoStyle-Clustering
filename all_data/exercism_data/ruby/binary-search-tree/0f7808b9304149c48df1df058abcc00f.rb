class Bst
  attr_reader :data, :left, :right
  def initialize(element)
    @data  = element
    @left  = nil
    @right = nil
  end

  def insert(element)
    add element, side(element)
  end

  def each(&block)
    to_a.each(&block)
  end

  def to_a
    [@left.to_a, @data, @right.to_a].flatten
  end

  private

  # def add_left(element)
  #   @left ? @left.insert(element) : (@left = self.class.new(element))
  # end
  #
  # def add_right(element)
  #   @right ? @right.insert(element) : (@right = self.class.new(element))
  # end
  #
  # def add(element, side_symb)
  #   send("add_#{side_symb}", element)
  # end

  def add(element, side_symb)
    send(side_symb).insert element
  rescue NoMethodError
    instance_variable_set "@#{side_symb}", self.class.new(element)
  end

  def side(element)
    element <= @data ? :left : :right
  end
end
