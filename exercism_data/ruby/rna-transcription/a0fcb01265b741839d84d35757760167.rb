class Complement
  DNA_TO_RNA = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna fold
    convert fold, DNA_TO_RNA
  end
  def self.of_rna fold
    convert fold, RNA_TO_DNA
  end

  private

  def self.convert fold, conversion_table
    fold.chars.map { |c| conversion_table[c] }.join
  end

end
