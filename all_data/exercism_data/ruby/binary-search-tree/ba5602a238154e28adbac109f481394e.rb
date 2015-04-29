class Bst

  attr_reader :data
  attr_accessor :left, :right

  def initialize data
    @data = data
  end

  def insert new_data
    child = new_data <= data ? :left : :right

    perform_insertion new_data, child
  end

  def each
    left.each  { |data| yield data } if left
    yield data
    right.each { |data| yield data } if right
  end

private

  def perform_insertion new_data, child_name
    child_node = send child_name

    if child_node
      child_node.insert new_data
    else
      send :"#{ child_name }=", Bst.new(new_data)
    end
  end

end
