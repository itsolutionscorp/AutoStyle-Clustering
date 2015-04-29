class Bst
  attr_reader :data
  attr_reader :left
  attr_reader :right

  def initialize(d)
    @data = d
  end

  def insert(d)
    case d <=> @data
    when -1, 0
      @left.nil? ? @left = Bst.new(d) : @left.insert(d)
    when 1
      @right.nil? ? @right = Bst.new(d) : @right.insert(d)
    end
  end

  def each
    to_a.each { |x| yield x }
  end

  def to_a
    @left.to_a + [@data] + @right.to_a
  end
end
