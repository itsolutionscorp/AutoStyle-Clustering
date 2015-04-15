class Bst
  attr_accessor :data, :left, :right
  
  def initialize(number, left=nil, right=nil)
    @data = number
    @left = left
    @right = right
  end
  
  def insert(number)
    case number <=> @data
    when -1, 0
      if self.left
        self.left.insert(number)
      else
        self.left = Bst.new(number)
      end
    when 1
      if self.right
        self.right.insert(number)
      else
        self.right = Bst.new(number)
      end
    else
      puts "an error occurred"
    end
  end
  
  def each(&block)
    left.each(&block) if left
    yield data
    right.each(&block) if right
  end
end
