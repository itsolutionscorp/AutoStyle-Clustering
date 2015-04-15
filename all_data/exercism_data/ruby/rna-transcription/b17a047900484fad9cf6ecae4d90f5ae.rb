class Complement

  DNA_SEQUENCE = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_SEQUENCE = DNA_SEQUENCE.invert

  def self.of_dna(dna)
    return DNA_SEQUENCE[dna] if dna.length == 1
    dna.chars.map { |strand|
      DNA_SEQUENCE[strand]
    }.join()
  end

  def self.of_rna(rna)
    return RNA_SEQUENCE[rna] if rna.length == 1
    rna.chars.map { |strand|
      RNA_SEQUENCE[strand]
    }.join()
  end

end
