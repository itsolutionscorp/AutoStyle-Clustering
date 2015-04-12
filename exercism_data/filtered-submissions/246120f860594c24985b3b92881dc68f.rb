class Hamming
	class << self
		def compute(dna_one, dna_two)
		    dna_one.split('').select.with_index {|c, index| (c != dna_two.split('')[index])}.count
		end
	end
end
