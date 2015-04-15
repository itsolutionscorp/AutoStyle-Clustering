class Bst
  attr_reader :data, :left, :right

  def initialize data
    @data = data
  end

  def insert num
    if num <= data
      !left ? @left = Bst.new(num) : left.insert(num)
    else
      !right ? @right = Bst.new(num) : right.insert(num)
    end
  end

  def each 
    result = []
    left.each {|ldata| result << ldata} if left
    result << data
    right.each {|rdata| result << rdata} if right
    result.each {|num| yield num}
  end

end
