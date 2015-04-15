class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(strand2)
  	pairs = @strand.chars.zip(strand2.chars)
  	pairs.count do |pair| 
  		pair.reject {|e| e.nil? }.uniq.length != 1
  	end
  end
end
