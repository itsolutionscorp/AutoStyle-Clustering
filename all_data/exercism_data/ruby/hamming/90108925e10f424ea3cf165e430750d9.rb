class DNA
  def initialize strand
    @dna = strand
  end

  def hamming_distance other
    distance = 0
    @dna.each_char do |c|
      distance += 1 unless same_nucleotide? c, other.slice!(0)
    end
    distance
  end

  private

  def same_nucleotide? n1,n2
    (n1.nil? ^ n2.nil?) || (n1 == n2)
  end
end
