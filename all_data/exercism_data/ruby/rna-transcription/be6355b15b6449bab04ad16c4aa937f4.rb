# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`

class Complement

@@dna = ['G', 'C', 'T', 'A']
@@rna = ['C', 'G', 'A', 'U']

	def self.of_dna(value)
		x = value.chars
		indexes = x.map { |y| @@dna.index(y) }
		result = indexes.map { |y| @@rna[y] }
		return result.join
	end

	def self.of_rna(value)
			# return !self.of_dna(value)
		x = value.chars
		indexes = x.map { |y| @@rna.index(y) }
		result = indexes.map { |y| @@dna[y] }
		return result.join
	end

end
