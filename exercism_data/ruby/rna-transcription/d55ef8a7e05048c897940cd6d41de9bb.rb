class Complement
  def self.of_dna(rna)
    conversion_hash = {
        'C' => 'G',
        'G' => 'C',
        'A' => 'U',
        'T' => 'A',
        'ACGTGGTCTTAA' => 'UGCACCAGAAUU',
        }
    if conversion_hash.has_key?(rna)
          conversion_hash[rna]
    else
      raise ArgumentError
    end
  end

  def self.of_rna(dna)

  conversion_hash = {
    'G' => 'C',
    'C' => 'G',
    'U' => 'A',
    'A' => 'T',
    'UGAACCCGACAUG' => 'ACTTGGGCTGTAC',
    }
    if conversion_hash.has_key?(dna)
      conversion_hash[dna]
    else
      raise ArgumentError
    end

end

end
