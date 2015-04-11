class Complement
	NUCLEOTIDES = [%w(G C T A), %w(C G A U)]
	DNA = 0
	RNA = 1
	def self.of_dna(strand)
		convert_strand DNA, RNA, strand
	end
	
	def self.of_rna(strand)
		convert_strand RNA, DNA, strand
	end
	
private
	def self.convert_strand(from, to, strand)
		strand.chars.map { |nucleotid| convert_nucleotid(from, to, nucleotid) }.join
	end

	def self.convert_nucleotid(from, to, nucleotid)
		NUCLEOTIDES[to][NUCLEOTIDES[from].index(nucleotid)]
	end
end
