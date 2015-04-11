class Complement
  DNA_BASES_REGEX = /A|T|C|G/
  RNA_BASES_REGEX = /A|U|C|G/

  DNA_COMPLEMENTS = 
    {'G' => 'C',
     'C' => 'G',
     'T' => 'A',
     'A' => 'U'
  }

  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  def self.of_dna(input)
    complement(input, DNA_BASES_REGEX, DNA_COMPLEMENTS)
  end

  def self.of_rna(input)
    complement(input, RNA_BASES_REGEX, RNA_COMPLEMENTS)
  end

  def self.complement(input, input_regex, complements_list)
    input.gsub(input_regex, complements_list)
  end
end
