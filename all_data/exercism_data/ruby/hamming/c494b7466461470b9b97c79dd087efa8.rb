class Hamming
  def self.compute(strand_1, strand_2)
  	distance = 0
  	length = strand_1.length > strand_2.length ? strand_2.length : strand_1.length
  	(0..(length - 1)).each do |i|
  		strand_1[i] != strand_2[i] ? distance += 1 : ''
  	end
    distance
  end
end
