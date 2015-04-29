class Bst
  attr_reader :data, :left, :right
  def initialize head
    @data = head
    @left = nil
    @right = nil
  end

  def insert data
    if data <= @data
      @left.nil? ? @left = Bst.new(data) : @left.insert(data)
    else data > @data
      @right.nil? ? @right = Bst.new(data) : @right.insert(data)
    end
  end

  def each &block
    @left.each &block if !@left.nil?
    yield data
    @right.each &block if !@right.nil?
  end

end
