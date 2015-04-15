class Element
  class << self
    def to_a(head_element)
      list = []
      element = head_element
      while(element)
        list << element.datum
        element = element.next
      end
      list
    end

    def from_a(list)
      list.to_a.reverse.reduce(nil) do |previous, datum|
        Element.new(datum, previous)
      end
    end
  end

  attr_reader :datum
  attr_accessor :next
  attr_accessor :prev
  include Enumerable

  def initialize(value, next_element, prev_element)
    @datum = value
    @next = next_element
    @prev = prev_element
  end

  def each
    element = self
    while(element)
      yield element.datum
      element = element.next
    end
  end

  def reverse
    head = nil
    previous_element = nil
    element = self

    while(element)
      head = Element.new(element.datum, previous_element)
      previous_element = element
      element = element.next
    end

    head
  end
end

class Deque
  attr_reader :head, :tail
  def initialize
    @head = nil
    @tail = nil
  end

  def push(data)
    if head.nil?
      @head = Element.new(data, nil, nil)
      @tail = head
    else
      new_element = Element.new(data, head, nil)
      @head.prev = new_element
      @head = new_element
    end
  end

  def pop
    unless head.nil?
      datum = head.datum
      @head = head.next
      datum
    end
  end

  def shift
    datum = tail.datum
    @tail = tail.prev
    @tail.next = nil if @tail
    datum
  end

  def unshift(data)
    if head.nil?
      @head = Element.new(data, nil, nil)
      @tail = head
    else
      new_element = Element.new(data, nil, tail)
      @tail.next = new_element
      @tail = new_element
    end
  end
end
