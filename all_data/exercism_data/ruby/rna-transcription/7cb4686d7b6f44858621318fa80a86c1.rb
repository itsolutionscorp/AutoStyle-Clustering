module Complement
  OF_RNA_MAP = {
    'G' => 'C',
    'U' => 'A',
    'A' => 'T',
    'C' => 'G'
  }

  OF_DNA_MAP = {
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
    'C' => 'G'
  }

  def self.of_rna(rna)
    complement_for(rna, OF_RNA_MAP)
  end

  def self.of_dna(dna)
    complement_for(dna, OF_DNA_MAP)
  end

  def self.complement_for(string, complement_map)
    string.each_char.map { |char| complement_map[char] }.join
  end
end
