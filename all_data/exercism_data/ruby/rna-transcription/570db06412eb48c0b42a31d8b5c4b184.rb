module Complement
  COMPLEMENTS_DNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  COMPLEMENTS_RNA = {
    'G' => 'C',
    'C' => 'G',
    'U' => 'A',
    'A' => 'T'
  }


  def self.of_dna(sequence)
    convert(sequence, COMPLEMENTS_DNA)
  end

  def self.of_rna(sequence)
    convert(sequence, COMPLEMENTS_RNA)
  end

  def self.convert(sequence, complements)
    result = ""

    sequence.to_s.chars do |chr|
      result += complements[chr]
    end

    result
  end

end
