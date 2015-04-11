class Hamming
  def self.compute(first_strand, second_strand)
    dna_pair = DNAPair.new(first_strand, second_strand)

    dna_pair.reduce(0) do |distance, base_pair|
      if base_pair.match?
        distance
      else
        distance + 1
      end
    end
  end
end

class DNAPair
  include Enumerable

  def initialize(first_strand, second_strand)
    @first_strand  = first_strand
    @second_strand = second_strand
  end

  def each
    0.upto shortest_length - 1 do |index|
      yield(BasePair.new(@first_strand[index], @second_strand[index]))
    end
  end

  private

  def sorted_strands
    [@first_strand, @second_strand].sort_by { |strand| strand.length }
  end

  def shortest_length
    sorted_strands.first.length
  end
end

class BasePair
  def initialize(first_base, second_base)
    @first_base  = first_base
    @second_base = second_base
  end

  def match?
    @first_base == @second_base
  end
end
