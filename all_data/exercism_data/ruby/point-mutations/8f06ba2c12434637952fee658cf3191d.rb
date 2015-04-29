class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(strand2)
  	pairs = @strand.chars.zip(strand2.chars)
  	pairs.count do |x, y| 
  		x != y unless y.nil?
  	end
  end
end
