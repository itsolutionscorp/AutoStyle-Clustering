class Bst
  attr_reader :left, :right, :data

  def initialize(data)
    @left = nil
    @right = nil
    @data = data
  end

  def insert(data)
    if data <= @data
      insert_left(data)
    else
      insert_right(data)
    end
  end

  def each(&block)
    @left.each(&block) unless @left.nil?
    yield @data
    @right.each(&block) unless @right.nil?
  end

  private

  def insert_left(data)
    if @left.nil?
      @left = Bst.new(data)
    else
      @left.insert(data)
    end
  end

  def insert_right(data)
    if @right.nil?
      @right = Bst.new(data)
    else
      @right.insert(data)
    end
  end
end
