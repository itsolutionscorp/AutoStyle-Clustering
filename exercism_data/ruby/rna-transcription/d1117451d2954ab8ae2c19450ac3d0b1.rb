class Complement
  @complements = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  @convert = ->(f,g) { f.chars.map {
      |x| g[x]
    }.join
  }
  def self.of_dna(dna_string)
    @convert.(dna_string, @complements)
  end
  def self.of_rna(rna_string)
    @convert.(rna_string, @complements.invert)
  end
end
