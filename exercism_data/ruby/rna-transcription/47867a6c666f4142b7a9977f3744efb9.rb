class Complement

  @dna_to_rna = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    rename strand, @dna_to_rna
  end

  def self.of_rna(strand)
    rename strand, @dna_to_rna.invert
  end

  private
  def self.rename(strand, table)
    strand.chars.map do |chr|
      table[chr]
    end.join("")
  end
end
