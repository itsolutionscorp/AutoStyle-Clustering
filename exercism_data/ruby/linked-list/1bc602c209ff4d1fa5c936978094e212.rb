class Deque
  Element = Struct.new(:content, :next, :prev)

  def initialize
    @sentinel = Element.new(nil)
    @sentinel.next = @sentinel.prev = @sentinel
  end

  def push content
    insert_before @sentinel, content
  end

  def unshift content
    insert_before @sentinel.next, content
  end

  # the sentinel element comes after the last element,
  # and before the first element.
  # therefore last == sentinel.prev
  # and first == sentinel.next
  def pop
    remove(@sentinel.prev)
  end

  def shift
    remove(@sentinel.next)
  end

  private def insert_before next_el, content
    el = Element.new(content, next_el, next_el.prev)
    next_el.prev.next = el
    next_el.prev = el
  end

  private def remove el
    el.prev.next = el.next
    el.next.prev = el.prev
    el.content
  end
end
