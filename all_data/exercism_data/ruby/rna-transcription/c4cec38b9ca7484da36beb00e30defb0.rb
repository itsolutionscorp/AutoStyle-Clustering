class Complement

	H = {
		"G" => "C",
	 	"C" => "G",
	 	"T" => "A", 
	 	"A" => "U"
	}

	def self.of_dna(dna)
		check_hash(dna, "dna")
		array = Array.new
		dna.split('').each { |i| array << H[i]}
		join_dna(array)
	end

	def self.of_rna(dna)
		check_hash(dna,"rna")
		array = Array.new
		dna.split('').each { |i| array << H.invert()[i]}
		join_dna(array)
	end

	def self.check_hash(strain, type)
		strain.split('').each do |n|	
			if type.eql? "dna"
				raise ArgumentError, "Wrong argument" unless ["G","C","T","A"].include? n	
			else
				raise ArgumentError, "Wrong argument" unless ["C","G","A","U"].include? n
			end
		end
	end

	def self.join_dna(b)
		conca = ""
		b.each { |i| conca << i}
		conca
	end
end

puts Complement.of_dna('A')
