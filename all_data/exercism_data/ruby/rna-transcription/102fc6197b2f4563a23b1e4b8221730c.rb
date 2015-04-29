class Complement

  @@rna_complements = { "G" => "C",
	                    "C" => "G",
	                    "T" => "A",
	                    "A" => "U"}

	@@dna_complements = @@rna_complements.invert

  def self.of_dna(dna)
    get_complement(dna, @@rna_complements)
	end

	def self.of_rna(rna)
	  get_complement(rna, @@dna_complements)
  end

  private 
    def self.get_complement(input, translation_map)
  	  output = ""
  	  input.each_char { |nucleotide| output << translation_map[nucleotide] }
  	  output
    end

end
