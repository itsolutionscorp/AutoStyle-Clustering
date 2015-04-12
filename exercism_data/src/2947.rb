class Hamming

  def compute(strand1, strand2)
    distance = 0
    len = [strand1.length, strand2.length].min

    (0...len).each do |i|
    	puts i
    	if strand1[i] != strand2[i]
    		distance += 1
    	end
    end
    
    return distance 
  end

end
