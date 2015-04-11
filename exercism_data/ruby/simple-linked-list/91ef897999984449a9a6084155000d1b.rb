class Element
  attr_reader :datum, :next

  def self.to_a(element)
    element.to_a
  end

  def self.from_a(source)
    source.to_a.reverse.reduce(nil) do |list, datum| 
      self.new datum, list
    end
  end


  def initialize(datum, next_one)
    @datum = datum
    @next = next_one
  end

  def to_a
    map { |i| i }
  end

  def map(&block)
    return to_enum(__method__) unless block_given?

    array = []
    each { |i| array << yield(i) }
    array
  end
  
  def each
    return to_enum unless block_given?

    e = self
    until e.nil?
      yield e.datum
      e = e.next
    end
  end


  def reverse
    self.class.from_a to_a.reverse
  end


  def reduce(initial, &block)
    return to_enum(__method__) unless block_given?

    acc = initial
    each { |e| acc = yield acc, e }
    acc
  end
end

class LinkedListTest < MiniTest::Unit::TestCase
  def test_reduce
    assert_equal 3, @two.reduce(0, &:+)
  end

  def test_map
    assert_equal [4, 2], @two.map { |i| i * 2 }
  end
end
