class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
  end

  def insert(child_data)
    child_data <= data ? insert_left(child_data) : insert_right(child_data)
  end

  def each(&block)
    return enum_for(:self) unless block_given?

    left.each(&block) if left
    yield data
    right.each(&block) if right
  end

  private

  def insert_left(child_data)
    if @left
      @left.insert(child_data) 
    else
      @left = Bst.new(child_data)
    end
  end

  def insert_right(child_data)
    if @right
      @right.insert(child_data)
    else
      @right = Bst.new(child_data)
    end
  end
end
