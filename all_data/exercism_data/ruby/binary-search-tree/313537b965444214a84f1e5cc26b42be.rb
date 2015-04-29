class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
    @left = nil
    @right = nil
  end

  def insert(n)
    if n <= @data
      @left ? @left.insert(n) : @left = Bst.new(n)
    else
      @right ? @right.insert(n) : @right = Bst.new(n)
    end
  end

  def each(&block)
    @left.each(&block) if @left
    yield(@data) if block_given?
    @right.each(&block) if @right
  end
end
