class Bst
  attr_reader :data
  attr_accessor :left, :right

  def initialize data
    @data = data
    @left = nil
    @right = nil
  end

  def insert new_data
    target = new_data <= @data ? 'left' : 'right'
    if send(target).nil?
      send("#{target}=", (Bst.new new_data))
    else
      send(target).insert(new_data)
    end
  end

  def each &block
    left.each &block unless left.nil?
    yield data
    right.each &block unless right.nil?
  end
end
