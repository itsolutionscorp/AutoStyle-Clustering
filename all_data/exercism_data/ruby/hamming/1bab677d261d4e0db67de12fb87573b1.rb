class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    ClippedStrings.new(strand, other_strand).select {|char_pair|
      char_pair[0] != char_pair[1]
    }.size
  end

end


class ClippedStrings
  include Enumerable

  attr_reader :first, :second

  def initialize(first='', second='')
    @first, @second = clip(first, second)
  end

  def clip(a, b)
    last = [a.size, b.size].min
    [a.slice(0, last), b.slice(0, last)]
  end

  def size
    first.size
  end

  def each_char
    (0..(first.size - 1)).to_a.each {|i|
      yield [first[i], second[i]]
    }
  end

  def each(&block)
    each_char &block
  end

end
