class DNA
  attr_reader :dna_strand

  def initialize(dna_strand)
    @dna_strand = dna_strand
  end

  def count(strand)
    valid?(strand) ? dna_strand.count(strand) : (raise ArgumentError)
  end

  def valid?(strand)
    ["A", "T", "C", "G", "U"].include?(strand)
  end

  def nucleotide_counts
    counts = {"A"=>0, "T"=>0, "C"=>0, "G"=>0}

    dna_strand.chars.uniq.each do |nucleotide|
      counts[nucleotide] = dna_strand.count(nucleotide)
    end
    
    counts
  end
end
