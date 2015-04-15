class DNA


  def initialize(dna_strand)
    @dna = valid(dna_strand)
    @valid_nucleotides = ["A", "C", "T", "G"]
    @nucleotides = nucleotide_hash
    count_nucleotides
  end

  def count(nucleotide)
    @nucleotides[valid(nucleotide)]
  end

  def nucleotide_counts
    @nucleotides
  end

  private

  def valid(strand)
    raise ArgumentError if strand.match(/[^ACTG]/)
    strand
  end

  def count_nucleotides
    @dna.each_char { |char| @nucleotides[char] += 1 }
  end

  def nucleotide_hash
    @valid_nucleotides.each_with_object({}) { |nucleotide, hash| hash[nucleotide] = 0 }
  end
end
