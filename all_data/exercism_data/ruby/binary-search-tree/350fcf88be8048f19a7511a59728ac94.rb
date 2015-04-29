class Object
  def try(method, *args, &block)
    if respond_to?(method, *args, &block)
      __send__(method, *args, &block)
    else
      nil
    end
  end
end


class Bst
  include Enumerable

  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
    @left = nil
    @right = nil
  end

  def insert(data)
    subtree(data) do |subtree|
      subtree.try(:insert, data) || self.class.new(data)
    end
    self
  end

  def each(&block)
    left.each(&block) if left
    yield self.data
    right.each(&block) if right
  end

  private

  attr_writer :left, :right

  def subtree(data, &block)
    side = data <= self.data ? 'left' : 'right'
    subtree = send(side)
    send("#{side}=", yield(subtree))
  end
end
