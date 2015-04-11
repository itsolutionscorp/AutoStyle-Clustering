class Bst
  attr_reader   :data
  attr_accessor :left, :right

  def initialize data
    @data = data
  end

  def insert new_data
    insert_node( new_data, position_for( new_data ) )
  end

  def each
    left.each  { |data| yield data } if left
    yield data
    right.each { |data| yield data } if right
  end

private
  
  def position_for child_data
    child_data <= data ? :left : :right 
  end

  def insert_node data, position
    node = send position
    node ? node.insert( data ) : send( :"#{position}=", Bst.new( data ) )
  end

end
