class Element
  attr_reader :datum, :next

  def initialize(datum, next_one)
    @datum = datum
    @next = next_one
  end

  def self.from_a(source)
    source.to_a.reverse.reduce(nil) do |list, datum| 
      Element.new datum, list
    end
  end

  def each
    return to_enum unless block_given?

    e = self
    until e.nil?
      yield e
      e = e.next
    end
  end

  def reduce(initial, &block)
    return to_enum(__method__) unless block_given?

    acc = initial
    each { |e| acc = yield acc, e }
    acc
  end

  def map(&block)
    return to_enum(__method__) unless block_given?
      
    array = []
    each { |e| array << yield(e) }
    array
  end

  def to_a
    map { |e| e.datum }
  end

  def self.to_a(element)
    element.to_a
  end

  def reverse
    Element.from_a to_a.reverse
  end
end
