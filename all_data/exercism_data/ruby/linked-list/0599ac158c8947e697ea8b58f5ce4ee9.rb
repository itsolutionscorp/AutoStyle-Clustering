class Deque
  def initialize
    self.sentinel = Element.new(nil)
  end

  def push(value)
    Element.new(value).insert(sentinel.prev, sentinel)
  end

  def pop
    sentinel.prev.unlink.value
  end

  def shift
    sentinel.next.unlink.value
  end

  def unshift(value)
    Element.new(value).insert(sentinel, sentinel.next)
  end

  private

  attr_accessor :sentinel
end


class Element
  attr_accessor :prev, :next
  attr_reader :value

  def initialize(value)
    @value = value
    self.next = self.prev = self
  end

  def insert(preceding, following)
    preceding.next = self
    self.prev = preceding
    self.next = following
    following.prev = self
    self
  end

  def unlink
    self.prev.next = self.next
    self.next.prev = self.prev
    self
  end
end
