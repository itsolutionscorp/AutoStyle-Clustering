class Hamming

	def self.compute(strandOne, strandTwo)
		sum = 0

    strandOne.chars.each_with_index do |c,i|
      (sum += 1) unless c == strandTwo[i]
		  #sum += c != strandTwo[i] ? 1 : 0
		end

		sum
	end
end
