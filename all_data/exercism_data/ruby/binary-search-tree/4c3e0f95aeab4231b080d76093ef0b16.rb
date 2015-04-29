class Bst
  attr_accessor :data, :left, :right
  def initialize(number)
    @data = number
  end

  def insert(number)
      if (number <= @data)
        handle_left_insertion(number)
      else
        handle_right_insertion(number)
      end
  end

  def each(&block)
    @left && @left.each(&block)
    block.call(@data)
    @right && @right.each(&block)
  end


  private
  def handle_left_insertion(number)
    if (@left.nil?)
      @left = Bst.new(number)
    else
      @left.insert(number)
    end
  end

  def handle_right_insertion(number)
    if (@right.nil?)
      @right = Bst.new(number)
    else
      @right.insert(number)
    end
  end

end
