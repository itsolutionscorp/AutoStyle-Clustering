module Hamming
  module_function

  def compute dna_strand_a, dna_strand_b
    diff = 0
    each_base_pair(dna_strand_a, dna_strand_b) do |a, b|
      diff += 1 if b && a != b
    end
    diff
  end

  def each_base_pair dna_strand_a, dna_strand_b
    split(dna_strand_a).each_with_index do |a, index|
      yield a, dna_strand_b[index]
    end
  end
  private :each_base_pair

  def split strand
    strand.split('')
  end
  private :split

end
