class DNA

def initialize(dna)
	@dna = dna.upcase
	raise ArgumentError unless @dna.match(/^[AGCT]*$/)
	@nucleotides = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
	@dna.each_char {|c| @nucleotides[c]+=1 }
end

def nucleotide_counts()
	@nucleotides
end

def count(nucleotide)
	raise ArgumentError unless nucleotide.match(/^[AGCTU]*$/)
	@nucleotides[nucleotide] || 0
end

end
