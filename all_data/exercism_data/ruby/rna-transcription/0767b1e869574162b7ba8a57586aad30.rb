class Complement

  def self.of_dna(strand)
    self.convert(strand, RNA_replacements)
  end

  def self.of_rna(strand)
    self.convert(strand, DNA_replacements)
  end

  private

  def self.convert(sequence, type)
    sequence.chars.map{|nuc| type[nuc]}.join
  end

  RNA_replacements = {'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U'}
  DNA_replacements = {'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T'}

end
