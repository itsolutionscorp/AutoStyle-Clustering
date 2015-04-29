class DNA

  def initialize(strand)
    @strand = strand
  end


  def count(nucleotide) 
    raise ArgumentError unless /[AGTCU]/ =~ nucleotide
    nucleotide_counts[nucleotide] || 0
  end

  def nucleotide_counts
    histogram = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @strand.scan(/[AGTC]/).each do |nucleotide| 
      histogram[nucleotide] += 1
    end
    histogram
  end

end
