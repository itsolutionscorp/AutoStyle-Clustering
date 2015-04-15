class Hamming
  def self.compute(first_strand, second_strand)
    dna_pair = DNAPair.new(first_strand, second_strand)
    dna_pair.count(&:mutation?)
  end
end

class DNAPair
  include Enumerable

  def initialize(first, second)
    @first  = first
    @second = second
  end

  def each
    0.upto shortest_length - 1 do |index|
      yield(BasePair.new(@first[index], @second[index]))
    end
  end

  private

  def sorted_strands
    [@first, @second].sort_by { |strand| strand.length }
  end

  def shortest_length
    sorted_strands.first.length
  end
end

class BasePair
  def initialize(first, second)
    @first  = first
    @second = second
  end

  def mutation?
    @first != @second
  end
end
