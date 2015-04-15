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
  
  def traversal(output)
    if left != nil
      left.traversal(output)
    end

    output << data

    if right != nil
      right.traversal(output)
    end
  end
  
  def each
    output = []
    traversal(output)
    output.each {|n| yield n}
  end
end
