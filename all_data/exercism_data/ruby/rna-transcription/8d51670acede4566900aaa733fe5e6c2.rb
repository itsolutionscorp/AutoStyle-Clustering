
class Complement
	def self.of_dna(dna_strand)
		ComplimentaryRNAStrand.new(dna_strand).calculate_complimentary_strand()
	end
	
	def self.of_rna(rna_strand)
		ComplimentaryDNAStrand.new(rna_strand).calculate_complimentary_strand()
	end
	
end

class ComplimentaryDNAStrand
	def initialize(dna_or_rna_strand)
		@original_strand = dna_or_rna_strand
	end
	
	def calculate_complimentary_strand()
		begin
			complimentary_strand = ""
			@original_strand.each_char do |original_nucleotide|
				complimentary_strand += complimentary_nucleotide(original_nucleotide)
			end
			complimentary_strand
		rescue InvalidNucleotideException
			raise "Invalid original DNA or RNA strand."
		end
	end
	
	private
	def complimentary_nucleotide(nucleotide)
		return "A" if nucleotide == "T" || nucleotide == "U"
		return "T" if nucleotide == "A"
		return "C" if nucleotide == "G"
		return "G" if nucleotide == "C"
		raise InvalidNucleotideException
	end
	
	class InvalidNucleotideException < RuntimeError
	end
end

class ComplimentaryRNAStrand < ComplimentaryDNAStrand
	private
	def complimentary_nucleotide(nucleotide)
		return "U" if nucleotide == "A"
		super
	end
end
