class Bst
  attr_reader :data
  attr_reader :left
  attr_reader :right

  def initialize data
    @data = data
  end

  def insert num
    if num <= data
      if !left
        @left = Bst.new(num)
      else
        left.insert(num)
      end
    else
      if !right
        @right = Bst.new(num)
      else
        right.insert(num)
      end
    end
  end

  def each 
    result = []
    if left
      left.each {|ldata| result << ldata}
    end
    result << data
    if right
      right.each {|rdata| result << rdata}
    end
    result.each {|num| yield num}
  end

end
