module Hamming
  def self.compute(original_strand, mutated_strand)
    dna_list = DNAPair.new(original_strand, mutated_strand)

    dna_list.reject do |original_acid, mutated_acid|
      original_acid == mutated_acid
    end.size
  end
end

class DNAPair
  include Enumerable

  def initialize(*strands)
    @strands = strands
  end

  def each
    enumerators = @strands.map(&:each_char)

    yield enumerators.map(&:next) while true
  rescue StopIteration
    # No more nucleic acids for at least one strand
  end
end
