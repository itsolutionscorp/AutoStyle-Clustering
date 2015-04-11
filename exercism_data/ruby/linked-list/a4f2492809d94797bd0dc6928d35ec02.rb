class Symbol
  OPPOSITE = {
    :first => :last,
    :last => :first
  }

  INITIAL = {
    :first => :next_one,
    :last => :prev
  }

  BEFORE = {
    :first => :prev,
    :last => :next
  }

  AFTER = {
    :first => :next,
    :last => :prev
  }

  def to_setter
    (to_s + "=").to_sym
  end

  %w(OPPOSITE INITIAL BEFORE AFTER).each do |hash|
    define_method(hash.downcase) { eval(hash)[self] }
  end

end

class Element
  attr_accessor :value, :prev, :next

  def initialize(value, prev: nil, next_one: nil)
    @value = value
    @prev = prev
    @next = next_one
  end
end

class Deque
  attr_reader :length
  attr_accessor :first, :last

  def initialize(start_array = [])
    @length = 0
    start_array.each { |value| push(value) }
  end

  def push(value)
    add_value_at_element(value, :last)
  end

  def pop
    remove_element_at(:last)
  end

  def unshift(value)
    add_value_at_element(value, :first)
  end

  def shift
    remove_element_at(:first)
  end

  def [](index)
    find_nth_element(index).value
  end

  def []=(index, new_value)
    find_nth_element(index).value = new_value
  end

  private
  def add_value_at_element(value, element_getter)
    element = send(element_getter)
    e = Element.new value, element_getter.initial => element
    element.send(element_getter.before.to_setter, e) unless element.nil?
    send(element_getter.to_setter, e)
    opposite = element_getter.opposite
    send(opposite) || send(opposite.to_setter, e)
    @length += 1
  end

  def remove_element_at(element_getter)
    if @length > 0
      element = send(element_getter)
      value = element.value
      send(element_getter.to_setter, element.send(element_getter.after))
      element.send(element_getter.before.to_setter, nil) unless element.nil?
      @length -= 1
      value
    end
  end

  def find_nth_element(index)
    if index >= 0
      search_forward(index)
    else
      search_reverse(-index - 1)
    end
  end

  def search_forward(index)
    search_from(index, :first)
  end

  def search_reverse(index)
    search_from(index, :last)
  end

  def search_from(index, element_getter)
    after = element_getter.after
    index.times.reduce(send(element_getter)) do |current|
      break if current.nil?
      current.send after
    end
  end
end
