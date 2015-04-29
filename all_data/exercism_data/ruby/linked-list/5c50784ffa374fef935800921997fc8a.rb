class Deque
  Element = Struct.new(:datum, :prev, :next)

  def unshift(datum)
    tmp = Element.new(datum, nil, first)
    first ? first.prev = tmp : self.last = tmp
    self.first = tmp
  end

  def shift
    datum = first.datum
    self.first = first.next
    first.prev = nil if first
    datum
  end

  def push(datum)
    tmp = Element.new(datum, last, nil)
    first ? last.next = tmp : self.first = tmp
    self.last = tmp
  end

  def pop
    datum = last.datum
    self.last = last.prev
    last.next = nil if last
    datum
  end

  private

  attr_accessor :first, :last
end
