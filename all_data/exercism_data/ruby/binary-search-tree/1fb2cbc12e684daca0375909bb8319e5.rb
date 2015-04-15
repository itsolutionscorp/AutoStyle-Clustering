class Bst
  include Enumerable

  attr_reader :left, :right, :data

  def initialize(num)
    @left = nil
    @right = nil
    @data = num
  end

  def insert(num)
    if num <= data
      insert_or_create(:left, num)
    else
      insert_or_create(:right, num)
    end
  end

  def each(&block)
    traverse(:left, &block)
    yield data
    traverse(:right, &block)
  end

  private

  def traverse(node, &block)
    node = instance_variable_get("@#{node}")
    node.each(&block) unless node.nil?
  end

  def insert_or_create(node, num)
    the_node = instance_variable_get("@#{node}")
    if the_node.nil?
      instance_variable_set("@#{node}", new_node(num))
    else
      the_node.insert(num)
    end
  end

  def new_node(num)
    Bst.new(num)
  end
end
