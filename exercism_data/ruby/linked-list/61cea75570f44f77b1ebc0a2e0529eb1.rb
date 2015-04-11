class Element
  attr_accessor :next, :prev
  attr_reader :value

  def initialize(value)
    @value = value
  end
end

class Deque
  def push(item)
    e = Element.new(item)
    @first = e if @first.nil?
    unless @last.nil?
      @last.next = e
      e.prev = @last
    end
    @last = e
    item
  end

  def pop
    return if @last.nil?
    v = @last.value
    unless @last.prev.nil?
      @last = @last.prev
      @last.next = nil
    end
    v
  end

  def unshift(item)
    e = Element.new(item)
    unless @first.nil?
      @first.prev = e
      e.next = @first
    end
    @first = e
    @last = e if @last.nil?
    item
  end

  def shift
    return if @first.nil?
    v = @first.value
    unless @first.next.nil?
      @first = @first.next
      @first.prev = nil
    end
    v
  end
end
