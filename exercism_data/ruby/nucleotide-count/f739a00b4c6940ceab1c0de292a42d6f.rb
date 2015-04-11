class Nucleotide

	attr_accessor :histogram

	@@NUCLEOTIDES = "ATCG"
	
	def initialize(dna)
		raise ArgumentError if dna =~ /[^#{@@NUCLEOTIDES}]/
		
		self.histogram = countNucleotides(dna)
	end

	def self.from_dna(dna)
		new(dna)
	end

	def count(tide)
		histogram[tide]
	end

	private

		def countNucleotides(dna)
			dna.chars.each_with_object(emptyHistogram) do |tide, hash|
				hash[tide] += 1
			end
		end

		def emptyHistogram
			@@NUCLEOTIDES.chars.each_with_object({}) do |key, hash|
				hash[key] = 0
			end
		end

end
