class Deque
  def initialize
    @stack = []
  end

  def push(val)
    @stack.push(val)
  end

  def pop
    @stack.pop
  end

  def shift
    @stack.delete_at(0)
  end

  def unshift(val)
    @stack.reverse.each_with_index do |x, i|
      @stack[i+1] = x
    end

    @stack[0] = val
  end

end
