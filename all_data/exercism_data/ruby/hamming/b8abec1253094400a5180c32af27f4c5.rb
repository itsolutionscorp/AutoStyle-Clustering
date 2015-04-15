class DNA
  attr_reader :original_strand

  def initialize(input)
    @original_strand = process_strand(input)
  end

  def hamming_distance(target_strand)
    mutations  = 0

    target_dna   = process_strand(target_strand)
    original_dna = normalize_strand_to(target_strand)

    original_dna.each_with_index do |nucleotide, index|
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

  def normalize_strand_to(shorter_strand)
    length = shorter_strand.length - 1
    original_strand[0..length]
  end
end
