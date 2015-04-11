class Bst

  attr_accessor :data, :left, :right

  def initialize data
    @data = data
    @left = nil
    @right = nil
  end

  def insert n
    if n <= data
      @left.nil? ? @left = Bst.new(n) : @left.insert(n)
    else
      @right.nil? ? @right = Bst.new(n) : @right.insert(n)
    end
  end

  def each &block
    @left.each &block  unless @left.nil?
    yield(data)
    @right.each &block unless @right.nil?
  end
end
