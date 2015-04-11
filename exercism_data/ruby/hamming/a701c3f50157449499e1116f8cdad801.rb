class Hamming
	class << self
		def compute(dna_one, dna_two)
			count = 0
			dna_two_arr = dna_two.split('')
		    dna_one.split('').select.with_index {|c, index| (c != dna_two_arr[index])}.count
		end
	end
end
