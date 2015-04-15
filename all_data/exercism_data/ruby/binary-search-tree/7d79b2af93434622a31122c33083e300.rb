class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
    @left = nil
    @right = nil
    @branches = [@left, @right]
  end

  def insert(new_data)
    if new_data <= data
      insert_left new_data
    else
      insert_right new_data
    end
  end

  def each(&block)
    return to_enum unless block_given?

    search(@left, &block)
    yield @data
    search(@right, &block)
  end

  private
  def insert_left(new_data)
    @left && @left.insert(new_data) || @left = Bst.new(new_data)
  end

  def insert_right(new_data)
    @right && @right.insert(new_data) || @right = Bst.new(new_data)
  end

  def search(branch, &block)
    branch.each(&block) unless branch.nil?
  end
end
