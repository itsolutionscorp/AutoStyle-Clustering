class Element
  attr_accessor :next, :prev
  attr_reader :value
  def initialize(value, next_element = nil, prev_element = nil)
    @value = value
    @next = next_element || self
    @prev = prev_element || self
  end
end

class Deque
  def initialize
    @front = nil
  end

  def push(value)
    if @front.nil?
      @front = Element.new(value)
    else
      back = @front.prev
      elem = Element.new(value, @front, back)

      back.next = elem
      @front.prev = elem
    end
  end

  def unshift(value)
    push(value)
    @front = @front.prev
  end

  def pop
    @front = @front.prev
    shift
  end

  def shift
    value, front, back = @front.value, @front.next, @front.prev
    if front.equal?(@front)
      @front = nil
    else
      front.prev = back
      back.next = front
      @front = front
    end
    value
  end
end
