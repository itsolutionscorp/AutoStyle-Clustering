class Bst
  attr_reader :data, :left, :right

  def initialize n
    @data = n
    @left = @right = nil
  end

  def insert n
    if n <= @data
      if @left
        @left.insert n
      else
        @left = Bst.new n
      end
    else
      if @right
        @right.insert n
      else
        @right = Bst.new n
      end
    end
  end

  def each &block
    @left.each &block if @left
    if block_given?
      block.call  @data
    else
      yield  @data
    end
    @right.each &block if @right
  end

end
