class Bst
  attr_reader :data, :left, :right
  def initialize(data)
    @data = data
  end
  
  def insert(new_data)
    if new_data <= data 
      left ? left.insert(new_data) : @left = self.class.new(new_data)
    else
      right ? right.insert(new_data) : @right = self.class.new(new_data)
    end
  end

  def each &block
    left.each(&block) if left
    yield data
    right.each(&block) if right
  end
end
