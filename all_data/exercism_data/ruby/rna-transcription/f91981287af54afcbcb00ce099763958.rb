module Complement

	def ~ 
		self.of_dna self
	end

	def -@
		self.of_rna self
	end

	extend self
	
	TO_RNA = {'G' => 'C', 'C'=> 'G', 'T' => 'A', 'A' => 'U'}
	TO_DNA = TO_RNA.invert
	
	def of_dna(strand)
		transcribe strand, TO_RNA
	end

	def of_rna(strand)
		transcribe strand, TO_DNA 
	end
	
	private

	def transcribe(original,transcribe)
		strand = original.class.new
		original.size.times do |nucleotide|
			strand << transcribe[original[nucleotide]]
		end
		strand
	end
end
