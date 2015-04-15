=begin
* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
=end

class Complement
	def self.of_dna strand
		replace_nucleoid(strand, 'G', 'temporary')
		replace_nucleoid(strand, 'C', 'G')
		replace_nucleoid(strand, 'temporary', 'C')
		replace_nucleoid(strand, 'A', 'U')
		replace_nucleoid(strand, 'T', 'A')
		strand
	end
	def self.of_rna strand
		replace_nucleoid(strand, 'C', 'temporary')
		replace_nucleoid(strand, 'G', 'C')
		replace_nucleoid(strand, 'temporary', 'G')
		replace_nucleoid(strand, 'A', 'T')
		replace_nucleoid(strand, 'U', 'A')
		strand
	end

	private

	def self.replace_nucleoid strand, original_nucleoid, replacement_nucleoid
		strand.gsub!(original_nucleoid,replacement_nucleoid)
	end
end
