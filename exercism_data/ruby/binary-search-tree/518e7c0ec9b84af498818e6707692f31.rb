class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
  end

  def insert(node)
    if node <= @data
      @left.nil? ? @left = Bst.new(node) : @left.insert(node)
    else
      @right.nil? ? @right = Bst.new(node) : @right.insert(node)
    end
  end

  def each(&block)
    @left.each(&block) if @left
    yield @data
    @right.each(&block) if @right
  end

end
