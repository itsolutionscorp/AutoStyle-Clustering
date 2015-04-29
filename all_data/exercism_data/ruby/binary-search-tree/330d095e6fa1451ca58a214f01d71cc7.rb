class Bst
  attr_reader :data
  attr_accessor :left, :right

  def initialize(data)
    @data = data
  end

  def insert(new_data)
    element = self
    if element.left && (data >= new_data)
      element.left.insert(new_data)
    elsif element.right && (data <= new_data)
      element.right.insert(new_data)
    elsif new_data <= data
      element.left = Bst.new(new_data)
    else
      element.right = Bst.new(new_data)
    end
  end

  def each(&block)
    element = self
    result = []

    if element.left
      result = result + element.left.each
      if element.right
        result = result + element.right.each
      end
    elsif element.right
      result = result + element.right.each
      if element.left
        result = result + element.left.each
      end
    end

    result << data

    map_node_data(result, &block)
  end

  private

  def map_node_data(elements)
    if block_given?
      i = 0

      elements.sort!

      while i < elements.size
        yield elements[i]
        i += 1
      end
    else
      elements
    end
  end
end
