class DNA
  def initialize(strand)
    @strand = strand
  end
  
  def hamming_distance(strand2)
    mutual_length = [@strand.length, strand2.length].min
    (0...mutual_length).inject(0) do |distance, n|
      @strand[n] == strand2[n] ? distance : distance + 1
    end
  end
end
