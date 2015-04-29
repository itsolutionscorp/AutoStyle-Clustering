class Bst
  attr_reader :data, :left, :right

  def initialize(val=nil)
    @data = val
  end

  def insert(val)
    if val > @data
      @right ? @right.insert(val)
             : @right = Bst.new(val)

    else
      @left ? @left.insert(val)
            : @left = Bst.new(val)
    end
  end

  def each(&block)
    @left.each(&block) if @left
    yield @data
    @right.each(&block) if @right
  end
end
