class Bst
  attr_accessor :data, :left, :right

  def initialize(data)
    @data = data
  end
  
  def insert(value)
    direction = 
      case value <=> data
      when 1 then :right
      when -1, 0 then :left
      end
    directional_node = send(direction)  
    if directional_node.nil? 
      send("#{direction}=".to_sym, Bst.new(value))
    else
      directional_node.insert(value)
    end
  end 

  def each(&block)
    left.each(&block) if left
    yield data
    right.each(&block) if right
  end

end
