class Hamming
  def self.compute(a,b)
    hamming_distance = 0
    count = 0
    a.chars.each do|i|
	  unless b[count] == i then hamming_distance += 1 end
	  count += 1
	end
	return hamming_distance
  end
end
