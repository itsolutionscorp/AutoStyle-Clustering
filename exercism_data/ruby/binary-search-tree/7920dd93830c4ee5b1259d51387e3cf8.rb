class Bst
  attr_reader :data, :left, :right

  def initialize data
    @data = data
  end

  def each &block
    @left.each &block if @left
    yield @data
    @right.each &block if @right
  end

  def insert data
    if data <= @data
      insertLeft data
    else
      insertRight data
    end
  end

  private
  def insertLeft data
    if @left == nil
      @left = Bst.new data
    else
      @left.insert data
    end
  end

  def insertRight data
    if @right == nil
      @right = Bst.new data
    else
      @right.insert data
    end
  end
end
