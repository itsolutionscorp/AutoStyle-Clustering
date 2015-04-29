class Bst
  attr_reader :data, :left, :right

  def initialize(num)
    @data = num
    @left, @right = nil
  end

  def insert(num)
    node = Bst.new(num)
    if num <= data
      if @left.nil?
        @left = node
      else
        @left.insert(num)
      end
    else
      if @right.nil?
        @right = node
      else
        @right.insert(num)
      end
    end
  end

  def each(&block)
    left.each(&block)  unless left.nil?
    block.call(data)
    right.each(&block) unless right.nil?
  end
end
