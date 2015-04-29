class Deque
  attr_accessor :head

  def push(datum)
    if head.nil?
      @head = Element.new(datum, nil, nil)
    else
      fast_forward
      before = head
      @head = Element.new(datum, before, nil)
      @head.before.after = head
    end
  end

  def pop
    fast_forward
    datum = head.datum

    if head.after == head && head.before == head
      @head = nil
    else
      @head = head.before
      @head.before.after = head
    end

    datum
  end

  def unshift(datum)
    if head.nil?
      @head = Element.new(datum, nil, nil)
    else
      rewind
      after = head
      @head = Element.new(datum, nil, after)
      @head.after.before = head
    end
  end

  def shift
    rewind
    datum = head.datum

    if head.after == head && head.before == head
      @head = nil
    else
      @head = head.after
      @head.before = head
      @head.after.before = head
    end

    datum
  end

  private

  def rewind
    if head.before != head
      @head = head.before
      rewind
    end
  end

  def fast_forward
    if head.after != head
      @head = head.after
      fast_forward
    end
  end

end


class Element
  attr_accessor :datum, :before, :after

  def initialize(datum, before, after)
    @datum = datum
    @before = before || self
    @after = after || self
  end

end
