class Deque
  attr_reader :first, :last

  def initialize
    @first = nil
    @last = nil
  end

  def push(value)
    if last
      current = Node.new(prev: last, value: value)
      last.succ = current
      @last = current
    else
      @first = Node.new(value: value)
      @last = first
    end
  end

  def unshift(value)
    if first
      current = Node.new(succ: first, value: value)
      first.prev = current
      @first = current
    else
      @first = Node.new(value: value)
      @last = first
    end
  end

  def pop
    result = last

    if last
      @last = last.prev
      last.succ = nil if last
    end

    if first == result
      @first = nil
    end

    result.value
  end

  def shift
    result = first

    if first
      @first = first.succ
      first.prev = nil if first
    end

    if last == result
      @last = nil
    end

    result.value
  end

end

class Node
  attr_accessor :prev, :succ, :value

  def initialize(prev: nil, succ: nil, value: nil)
    @prev = prev
    @succ = succ
    @value = value
  end
end
