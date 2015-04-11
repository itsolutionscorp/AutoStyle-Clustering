class Bst
  def initialize(*initial_data)
    initial_data.each do |new_data|
      insert new_data
    end
  end

  attr_reader :data, :left, :right

  def insert(new_data)
    if data.nil?
      @data = new_data
    elsif new_data <= data
      @left ||= Bst.new
      left.insert new_data
    else
      @right ||= Bst.new
      right.insert new_data
    end
  end

  def each(&block)
    return enum_for(:each) unless block_given?

    left.each(&block) if left
    yield(data) if data
    right.each(&block) if right
  end
end
