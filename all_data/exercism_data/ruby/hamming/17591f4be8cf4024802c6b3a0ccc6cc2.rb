class DNA
  attr_reader :original_strand

  def initialize(input)
    @original_strand = process_strand(input)
  end

  def hamming_distance(target_strand)
    mutations  = 0
    target_dna = process_strand(target_strand)

    original_strand.each_with_index do |nucleotide, index|
      break if target_dna[index] == nil

      if target_dna[index] != nucleotide
        mutations += 1
      end
    end

    mutations
  end

  private

  def process_strand(strand)
    strand.split('')
  end
end
