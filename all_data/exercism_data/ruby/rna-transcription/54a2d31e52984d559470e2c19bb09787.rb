class Complement
  DNA_COMPLEMENTS = 
    {'G' => 'C',
     'C' => 'G',
     'T' => 'A',
     'A' => 'U'
  }

  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  def self.of_dna(input)
    complement(input, DNA_COMPLEMENTS)
  end

  def self.of_rna(input)
    complement(input, RNA_COMPLEMENTS)
  end

  def self.complement(input, complements_list)
    input.chars.inject("") do |output, char| 
      output << complements_list[char]
    end
  end
end