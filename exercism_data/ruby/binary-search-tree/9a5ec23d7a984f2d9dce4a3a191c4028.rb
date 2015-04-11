class Bst
  attr_reader :data, :left, :right
  def initialize (val)
    @data = val
  end
  def insert (val)
    if val <= @data
      if @left
        @left.insert(val)
      else
        @left = Bst.new(val)
      end
    else
      if @right
        @right.insert(val)
      else
        @right = Bst.new(val)
      end
    end
  end
  def each 
    @left.each{|i| yield(i)} if @left
    yield(@data)
    @right.each{|i| yield(i)} if @right
  end
end
