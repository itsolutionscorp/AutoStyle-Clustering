class Complement
	@@h1 = {'G'=>'C','C'=>'G','T'=>'A','A'=>'U'}
	@@h2 = @@h1.invert
	
	def self.of_dna(x) transcribe(x, @@h1) end
	def self.of_rna(x) transcribe(x, @@h2) end
	def self.transcribe(x, h)
		x.split('').inject('') { |result, c| result + h[c] }
	end
end
