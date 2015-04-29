class Element
  attr_accessor :datum, :next
  def initialize(datum, obj)
    @datum = datum
    @next = obj
  end

  def to_a
    results = []
    temp = self
    while true
      if temp.next
        results << temp.datum
        temp = temp.next
      else
        results << temp.datum
        break results
      end
    end
  end

  def self.to_a(obj)
    obj.to_a
  end

  def reverse
    if self.next
      temp = Element.new(self.next.datum, self)
    else
      temp = Element.new(self.datum, self.next)
    end
    temp
  end

  def self.from_a(arr)
    arr = arr.to_a if arr.is_a? Range
    return nil if arr.empty?

    list = List.new
    arr.each { |element| list.add(Element.new(element, nil)) }

    list.head
  end
end

class List
  attr_accessor :head, :tail
  def initialize
    @head = nil
    @tail = nil
  end

  def add(element)
    if @head
      if @tail
        @tail.next = element
        @tail = element
      else
        @head.next = element
        @tail = element
      end
    else
      @head = element
    end
  end
end
