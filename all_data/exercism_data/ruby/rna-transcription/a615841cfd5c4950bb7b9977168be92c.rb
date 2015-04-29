class Complement

	STRAIN_HASH = {
		
		"G" => "C",
	 	"C" => "G",
	 	"T" => "A", 
	 	"A" => "U"
	}

	def initialize(strain, type)
		@strain = strain
		@type = type
	end

	def self.of_dna(strain)
		ob = new(strain, 'dna')
		ob.check_hash
	end

	def self.of_rna(strain)
		ob = new(strain, 'rna')
		ob.check_hash
	end

	def check_hash
		if @type.eql? "dna"
			@strain.each_char.map { |w| STRAIN_HASH.fetch(w) }.join
		else
			@strain.each_char.map { |w| STRAIN_HASH.invert().fetch(w) }.join
		end
	end
end
