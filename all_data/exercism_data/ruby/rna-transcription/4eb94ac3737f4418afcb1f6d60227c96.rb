class String
	def mgsub(key_value_pairs=[].freeze)
		regexp_fragments = key_value_pairs.collect { |k,v| k }
		gsub(Regexp.union(*regexp_fragments)) do |match|
		key_value_pairs.detect{|k,v| k =~ match}[1]
		end
	end
end

class Complement
 
	def self.of_dna(rna_input)
	 dna_string = rna_input.mgsub([[/C/i, 'G'], [/G/i, 'C'], [/T/i, 'A'], [/A/i, 'U']])
	 dna_string
	end

	def self.of_rna(dna_input)
	 rna_string = dna_input.mgsub([[/C/i, 'G'], [/G/i, 'C'], [/A/i, 'T'], [/U/i, 'A']])
	 rna_string
	end
end
