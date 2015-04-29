class DNA
  attr_accessor :counts

  def initialize(dna_strand)
    @counts = {"A"=>0, "T"=>0, "C"=>0, "G"=>0}
    analyze_strand(dna_strand)
  end

  def analyze_strand(strand)
    strand.split('').uniq.each do |nucleotide|
      counts[nucleotide] = strand.count(nucleotide)
    end
  end

  def count(strand)
    valid?(strand) ? (counts[strand] || 0) : (raise ArgumentError)
  end

  def valid?(strand)
    ["A", "T", "C", "G", "U"].include?(strand)
  end

  def nucleotide_counts
    counts
  end
end
