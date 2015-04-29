class Element
  attr_reader :datum, :next
  def Element.to_a (e)
    case e
    when nil then []
    else [e.datum] + Element.to_a(e.next)
    end
  end
  def Element.from_a (arr)
    arr = arr.to_a.dup
    res = nil
    res = Element.new(arr.pop, res) while arr.size > 0
    res
  end
  def to_a
    Element.to_a(self)
  end
  def initialize (d, n)
    @datum = d
    @next = n
  end
  def reverse
    rev_aux self
  end
  def rev_aux (acc)
    case @next
    when nil then acc
    else @next.rev_aux(Element.new(@next.datum, acc))
    end
  end
end
