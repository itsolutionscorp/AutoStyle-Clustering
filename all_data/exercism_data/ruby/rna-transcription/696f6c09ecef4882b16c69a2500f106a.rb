class Complement

	DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	RNA_TO_DNA = DNA_TO_RNA.invert

	class << self
		
		def convert(strand, map)
			result = ''
			strand.each_char do |char|
				map.each do |original, replacement|
					if char == original then
						char = replacement
						break
					end
				end
				result << char
			end
			result
		end

		def of_dna(dna)
			convert(dna, DNA_TO_RNA)
		end

		def of_rna(rna)
			convert(rna, RNA_TO_DNA)
		end

	end
end

#puts Complement::REPLACEMENT_MAP.methods
