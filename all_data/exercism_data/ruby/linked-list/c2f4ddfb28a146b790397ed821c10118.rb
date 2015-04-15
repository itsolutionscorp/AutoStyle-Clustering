Cell = Struct.new :value, :next

class Deque
  attr_reader :list

  def initialize
    @list = Cell.new nil, nil
  end

  def unshift n
    @list = Cell.new n, list
    @list.next = nil if list.next.nil? && list.next.next.nil?
  end

  def push n
    reverse
    unshift n
    reverse
  end

  def pop
    s = reverse.shift
    reverse
    s
  end

  def shift
    s  = list.value
    if list.nil?
      @list.value = nil
      return s
    else
      @list = list.next
      return s
    end
  end

  def reverse
    r = Deque.new
    r.unshift shift until list.value.nil?
    @list = r.list
    self
  end
end
