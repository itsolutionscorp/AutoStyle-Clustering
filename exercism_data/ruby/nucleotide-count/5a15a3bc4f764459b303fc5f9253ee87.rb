class Nucleotide
       def initialize(strand)
	       @strand = strand
       end

       def self.from_dna(string)
	       raise ArgumentError if string.match(/[^ATGC]/)
	       Nucleotide.new(string)
       end

       def count(letter)
	       @strand.split(//).select{|x| x == letter}.length
       end

       def histogram
	       result = Hash.new
	       result['A'] = @strand.count('A')
	       result['T'] = @strand.count('T')
	       result['C'] = @strand.count('C')
	       result['G'] = @strand.count('G')
	       result
       end
end
