class Bst
  attr_accessor :data, :left, :right

  def initialize data
    @data = data
  end

  def insert number
    side = direction?(number)
    send(side) ? send(side).insert(number) : send("#{side}=", Bst.new(number))
  end

  def each &block
    left.each(&block) if left
    yield data
    right.each(&block) if right
  end

  private

  def direction? number
    number > data ? :right : :left
  end
end
