class Complement

  def self.of_dna(strain)
    complement_strain = ''

    pairs = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    strain.each_char do |nucleotide|
      complement_strain += pairs[nucleotide]
    end
    #return pairs[nucleotide]
    return complement_strain
  end

  def self.of_rna(strain)
    complement_strain = ''

    pairs = {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }

    strain.each_char do |nucleotide|
      complement_strain += pairs[nucleotide]
    end
    #return pairs[nucleotide]
    return complement_strain
  end
end
