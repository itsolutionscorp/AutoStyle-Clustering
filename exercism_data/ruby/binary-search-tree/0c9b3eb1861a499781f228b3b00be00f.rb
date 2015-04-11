class Bst
  attr_reader :data, :left, :right

  def initialize(data=nil)
    @data = data
    @left, @right = [NilBst.new] * 2
  end

  def insert value
    branch = value <= @data ? :left : :right
    send(branch).insert(value) || send("#{branch}=", Bst.new(value))
  end

  def each &blk
    left.each(&blk)
    blk.(data)
    right.each(&blk)
  end

  private
  attr_writer :data, :left, :right
end

class NilBst < Bst
  def initialize; end

  def insert value
    false
  end

  def each; end
end
