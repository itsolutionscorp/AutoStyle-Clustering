class Bst
  attr_accessor :data, :left, :right

  def initialize(data)
    @data = data
  end

  def insert(value)
    target = value <= data ? "left" : "right"
    send(target) ? send(target).insert(value) : send("#{target}=", Bst.new(value))
  end

  def each(&block)
    left.each(&block) if left
    block.call(data)
    right.each(&block) if right
  end
end
