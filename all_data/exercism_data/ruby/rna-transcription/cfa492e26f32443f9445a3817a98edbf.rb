class Complement
  DNA_TO_RNA_TABLE = {'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U'}
  RNA_TO_DNA_TABLE = DNA_TO_RNA_TABLE.invert

  def self.of_dna(strand)
    self.map_string_with_hash(strand, DNA_TO_RNA_TABLE)
  end

  def self.of_rna(strand)
    self.map_string_with_hash(strand, RNA_TO_DNA_TABLE)
  end

  def self.map_string_with_hash(string, hash)
    string.each_char.map { |element| hash[element] }.join
  end
end
