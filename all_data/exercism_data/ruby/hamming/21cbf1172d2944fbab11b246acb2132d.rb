# -----------------------------------------------------------------------------
class Hamming

  def self.compute(strand_1_s, strand_2_s)
    strands_pair        = Acids::StrandsPair.build(strand_1_s, strand_2_s)
    shortest_strand_len = strands_pair.shortest_strand_len

    return 0 if shortest_strand_len == 0

    nbr_of_mutations = 0
    shortest_strand_len.times {
      next_acids_pair   = strands_pair.next_acids_pair
      mutation_detected = next_acids_pair.mutation_detected?
      nbr_of_mutations += 1 if mutation_detected
    }
    nbr_of_mutations
  end
end

# -----------------------------------------------------------------------------
module Acids

  class Pair < Struct.new(:acid_1, :acid_2)
    def mutation_detected?
      acid_1 != acid_2
    end
  end


  class Strand
    attr_reader :length

    def initialize(strand_s)
      strand_s  ||= ''
      @length     = strand_s.length
      @acids_enum = Enumerator.new(strand_s.chars)
    end

    def next_acid
      @acids_enum.next
    end
  end


  class StrandsPair < Struct.new(:strand_1, :strand_2)
    def self.build(strand_1_s, strand_2_s)
      new(Strand.new(strand_1_s), Strand.new(strand_2_s))
    end

    def next_acids_pair
      Pair.new(strand_1.next_acid, strand_2.next_acid)
    end

    def shortest_strand_len
      [strand_1.length, strand_2.length].min
    end
  end
end
