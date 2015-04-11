class Bst
  attr_accessor :data, :left, :right

  def initialize(number)
    @data = number
  end

  def insert(number)
    @right.insert(number)    if (number  > @data) && @right
    @left .insert(number)    if (number <= @data) && @left
    @right = Bst.new(number) if (number  > @data) && @right.nil?
    @left  = Bst.new(number) if (number <= @data) && @left.nil?
  end

  def each(&block)
    @left.each(&block)  if @left
    yield data
    @right.each(&block) if @right
  end
end
