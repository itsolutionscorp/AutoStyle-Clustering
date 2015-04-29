class Bst
  attr_reader :data, :left, :right

  def initialize head
    @data = head
    @left, @right = nil, nil
  end

  def insert node
    if node <= data
      insert_left node
    else
      insert_right node
    end
  end

  def each &block
    make_enum.each(&block)
  end

  private

  def insert_left node
    if @left
      @left.insert node
    else
      @left = Bst.new node
    end
  end

  def insert_right node
    if @right
      @right.insert node
    else
      @right = Bst.new node
    end
  end

  def get_data
    get_data @left unless @left.nil?
    get_data @right unless @right.nil?
    data
  end

  def make_enum
    Enumerator.new do |x|
      left.each { |y| x << y } unless @left.nil?
      x << data
      right.each { |y| x << y } unless @right.nil?
    end
  end
end
