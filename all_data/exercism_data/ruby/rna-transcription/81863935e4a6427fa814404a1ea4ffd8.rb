class Complement

  PAIRS = { 
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  class << self

    def of_dna(dna)
      dna.chars.each_with_object("") do |base, rna|
        rna << PAIRS[base]
      end
    end

    def of_rna(rna)
      rna.chars.each_with_object("") do |base, dna|
        dna << PAIRS.invert[base]
      end
    end
  end
end
