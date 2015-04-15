class Complement
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_COMPLEMENTS = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }

  def self.of_dna(dna)
    translate DNA_COMPLEMENTS, dna
  end

  def self.of_rna(rna)
    translate RNA_COMPLEMENTS, rna
  end

  def self.translate(dict, str)
    str.split(//).map{|c| dict[c]}.join('')
  end
end
