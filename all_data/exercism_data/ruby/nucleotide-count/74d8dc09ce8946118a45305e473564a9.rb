class DNA
	DNA = ['A', 'C', 'G','T']
	RNA = [ 'A', 'C', 'G', 'U']
	def initialize(dna)
		 dna.split('').each do |i_dna|
			unless DNA.include? i_dna 
				raise ArgumentError
			end
		end
		@dna = dna
	end

	def count(letter)
		count = 0
		if DNA.include? letter or RNA.include? letter
				    count = @dna.count(letter)
		else
			
			raise ArgumentError
        end
       return count
	end

	def nucleotide_counts
      nucleotide_counts = {"A"=>0, "T"=>0, "C"=>0, "G"=>0}
      @dna.split('').each do |dna|

        if DNA.include? dna
        	nucleotide_counts[dna] = nucleotide_counts[dna] + 1
        else 
        	raise ArgumentError
        end

      end
      return nucleotide_counts
	end
end
