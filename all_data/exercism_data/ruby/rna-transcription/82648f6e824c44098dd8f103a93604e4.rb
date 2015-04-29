class Complement

	def self.gets_data
		puts "Enter DNA Strand"
		strand = gets.chomp
		of_dna(strand)
	end
	
	def self.clean_data(data)
		data.upcase!
		return data if /^[AGCT]+$/ === data 
		raise Runtime_Error("Must enter valid A,G,C,T nucleotides")
	end
	
	def self.of_dna(dna)
		clean_data(dna)
		rna_transcriber = {"A" => "U", "C" => "G", "G" => "C", "T" => "A"}
		rna = ""
		dna.size.times {|i| rna += rna_transcriber[dna[i]]}
		return rna
	
	rescue
		puts "Bad Data"
		gets_data
	end
	
end
