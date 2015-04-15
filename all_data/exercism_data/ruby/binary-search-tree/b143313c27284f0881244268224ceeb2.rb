class Bst
  def initialize(data)
    @data = data
  end
  attr_reader :data
  attr_reader :left
  attr_reader :right

  def insert(data)
    if @data < data
      insert_to_right data
    else
      insert_to_left data
    end
  end

  def each
    @left && @left.each { |x| yield x }
    yield data
    @right && @right.each { |x| yield x }
  end

  private

  def insert_to_left(data)
    if @left
      @left.insert data
    else
      @left = Bst.new(data)
    end
  end

  def insert_to_right(data)
    if @right
      @right.insert data
    else
      @right = Bst.new(data)
    end
  end
end
