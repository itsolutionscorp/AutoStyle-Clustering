class Complement
	OF_DNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	OF_RNA = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}

	def self.of_dna(source)
		out = ''
		source.each_char do |c| 
			out += OF_DNA[c]
		end
		out
	end

	def self.of_rna(source)
		out = ''
		source.each_char do |c|
			out += OF_RNA[c]
		end
		out
	end
end
