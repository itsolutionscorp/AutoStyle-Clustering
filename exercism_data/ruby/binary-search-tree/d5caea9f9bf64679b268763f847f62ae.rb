class Bst
  attr_reader :data, :left, :right

  def initialize(number)
    @data = number
  end

  def insert(number)
    if number > @data
      if @right.nil?
        @right = self.class.new(number)
      else
        @right.insert(number)
      end
    else
      if @left.nil?
        @left = self.class.new(number)
      else
        @left.insert(number)
      end
    end
  end

  def each(&block)
    sub_tree.each(&block)
  end

  def sub_tree
    (left.nil? ? [] : left.sub_tree) + [data] + (right.nil? ? [] : right.sub_tree)
  end
end
