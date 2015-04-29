class Complement
  RNA_MAP = {
    'T' => 'A',
    'G' => 'C',
    'A' => 'U',
    'C' => 'G'
  }

  DNA_MAP = {
    'A' => 'T',
    'C' => 'G',
    'U' => 'A',
    'G' => 'C'
  }
  
  def self.of_dna(strand)
    amino_mapping(strand, RNA_MAP)
  end

  def self.of_rna(strand)
    amino_mapping(strand, DNA_MAP)
  end

  class << self
    private

    def amino_mapping(string, hash)
      string.each_char.map {|c| hash[c]}.join('')
    end
  end
end
