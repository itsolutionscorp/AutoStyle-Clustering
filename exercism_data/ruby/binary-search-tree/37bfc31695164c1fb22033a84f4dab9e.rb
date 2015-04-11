class Bst

  attr_accessor :left,:data,:right

  def initialize(num)
    @left = nil
    @data = num
    @right = nil
  end

  def data
    @data    
  end

  def insert(num)
    if @data == nil
      @data = num
    elsif num == @data
      @data = num      
    elsif num > @data
      self.right.insert(num)
    elsif num < @data
      self.left.insert(num)
    end
  end

  def left
    if @left == nil
      @left = Bst.new(nil)
    else
      return @left
    end
  end

  def right
    if @right == nil
      @right = Bst.new(nil)
    else
      return @right
    end
  end

  def each
       
  end

end
