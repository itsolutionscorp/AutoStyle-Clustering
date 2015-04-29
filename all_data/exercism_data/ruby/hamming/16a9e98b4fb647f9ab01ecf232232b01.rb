class Hamming

	def self.compute(x,y)
		hamming_number = 0
		x.chars.each_with_index do |char, index|
			next if y[index].nil? 
			hamming_number+=1 if char != y[index]
		end
		hamming_number
	end
end
