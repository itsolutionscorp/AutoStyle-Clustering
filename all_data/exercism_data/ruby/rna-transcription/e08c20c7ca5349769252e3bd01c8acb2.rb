class Complement
  def self.of_dna(dna)
    dna.each_char.map { |char| DNA_RNA_PAIRS[char] }.join
  end

  def self.of_rna(rna)
    rna.each_char.map { |char| DNA_RNA_PAIRS.key char }.join
  end

  private
  DNA_RNA_PAIRS = {'A' => 'U', 'C' => 'G', 'G' => 'C', 'T' => 'A'}
end
