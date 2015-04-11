class Complement
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  def self.of_dna(dna)
    map_chars(dna) { |x| DNA_COMPLEMENTS[x] }
  end

  def self.of_rna(rna)
    map_chars(rna) { |x| DNA_COMPLEMENTS.key(x) }
  end

  def self.map_chars(string, &block)
    string.chars.map(&block).join
  end

end
