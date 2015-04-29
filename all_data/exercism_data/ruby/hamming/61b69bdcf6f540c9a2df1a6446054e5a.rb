class Hamming
  
	def self.compute(strandA, strandB)
		length = [strandA.size, strandB.size].min - 1
		a, b   = array(strandA, length), array(strandB, length)
		a.zip(b).reject { |pair| pair[0] == pair[1] }.count
	end

	private

	def self.array(str, length)
		str.split('')[0..length]
	end

end
