class Bst
  attr_reader :data, :left, :right
  def initialize datum
    @data = datum
  end
  
  def each &block
    @left.each &block unless @left.nil?
    yield data
    @right.each &block unless @right.nil?
  end
  
  def insert datum
    if datum > data
      insert_right datum
    else
      insert_left datum
    end
  end
  
  private
  def insert_left datum
    if @left.nil?
      @left = Bst.new datum
    else
      @left.insert datum
    end
  end
  
  def insert_right datum
    if @right.nil?
      @right = Bst.new datum
    else
      @right.insert datum
    end
  end
end
