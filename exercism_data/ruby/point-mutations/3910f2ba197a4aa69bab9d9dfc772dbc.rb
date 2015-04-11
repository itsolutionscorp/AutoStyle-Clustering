class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(strand2)
  	pairs = @strand.chars.zip(strand2.chars).first(strand2.length)
  	pairs.count do |x, y| 
  		x != y
  	end
  end
end
