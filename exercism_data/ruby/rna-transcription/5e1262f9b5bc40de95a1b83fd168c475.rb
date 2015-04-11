class Complement

  @dna_to_rna = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    convert strand, @dna_to_rna
  end

  def self.of_rna(strand)
    convert strand, @dna_to_rna.invert
  end

  private
  def self.convert(strand, table)
    strand.split("").map { |chr|
      table[chr]
    }.join("")
  end
end
