module Complement
  DNA_RNA_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.gsub(/\w/) { |letter| DNA_RNA_MAP[letter] }
  end

  def self.of_rna(rna)
    rna.gsub(/\w/) { |letter| DNA_RNA_MAP.key(letter) }
  end
end
