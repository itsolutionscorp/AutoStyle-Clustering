class Element
  attr_reader :datum, :next

  include Enumerable

  def self.to_a(element)
    element.to_a
  end

  def self.from_a(source)
    source.to_a.reverse.reduce(nil) do |element, datum| 
      self.new datum, element
    end
  end


  def initialize(datum, next_one)
    @datum = datum
    @next = next_one
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
end

class LinkedListTest < MiniTest::Unit::TestCase
  def test_reduce
    assert_equal 3, @two.reduce(0, &:+)
  end

  def test_map
    assert_equal [4, 2], @two.map { |i| i * 2 }
  end
end
