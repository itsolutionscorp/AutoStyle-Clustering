class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def count(nucleotide) 
    raise ArgumentError, "Invalid nucleotide: " unless valid?(nucleotide)
    strand.count(nucleotide)
  end

  def valid?(nucleotide)
    ["A", "T", "C", "G", "U"].include?(nucleotide)
  end

  def nucleotide_counts
    counts = {"A"=>0, "T"=>0, "C"=>0, "G"=>0}

    strand.chars.uniq.each do |nucleotide|
      counts[nucleotide] = strand.count(nucleotide)
    end
    
    counts
  end
end
