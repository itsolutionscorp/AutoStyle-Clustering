class Bst
  attr_accessor :data, :left, :right

  def initialize data
    @data = data
  end

  def insert number
    number > data ? insert_number("right", number) : insert_number("left", number)
  end

  def each &block
    left.each(&block) if left
    yield data
    right.each(&block) if right
  end

  private

  def insert_number side, number
    send(side) ? send(side).insert(number) : send("#{side}=", Bst.new(number))
  end
end
