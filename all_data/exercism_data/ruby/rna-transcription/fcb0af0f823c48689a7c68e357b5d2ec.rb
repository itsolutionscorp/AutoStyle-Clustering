class Complement

	def self.of_dna(dna)
		dnaClass = Dna.new
		dnaClass.strandValue = dna
		dnaClass.findComplement
	end

	def self.of_rna(rna)
		rnaClass = Rna.new
		rnaClass.strandValue = rna
		rnaClass.findComplement
	end
end

class Strand
	attr_accessor :strandValue
	RNA_STRAND = %w(C G A U)
	DNA_STRAND = %w(G C T A)
	
	def findComplement
		length = strandValue.length

		returnString = ""
		length.times do | index |
			characterToFind = strandValue[index]
			indexOfNucl = values.index(characterToFind)
			returnString += complement[indexOfNucl]
		end
		returnString
	end
end

class Rna < Strand
	def values
		RNA_STRAND
	end

	def complement
		DNA_STRAND
	end
end

class Dna < Strand
	def values
		DNA_STRAND
	end

	def complement
		RNA_STRAND
	end
end
