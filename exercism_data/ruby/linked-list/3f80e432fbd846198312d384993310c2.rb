class Deque
  Element = Struct.new(:content, :next, :prev)

  def push content
    if @last
      @last = Element.new(content, nil, @last)
      @last.prev.next = @last
    else
      @first = @last = Element.new(content)
    end
  end

  def unshift content
    if @first
      @first = Element.new(content, @first, nil)
      @first.next.prev = @first
    else
      @first = @last = Element.new(content)
    end
  end

  def pop
    value = @last.content
    @last = @last.prev
    value
  end

  def shift
    value = @first.content
    @first = @first.next
    value
  end
end
