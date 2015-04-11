class Complement
  def self.of_dna(strand)
    dna_to_rna = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    map_chars(strand, dna_to_rna)
  end

  def self.of_rna(strand)
    rna_to_dna = {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A' 
    }

    map_chars(strand, rna_to_dna)
  end

private

  def self.map_chars(strand, table)
    strand.each_char.map.with_index do |char|
      table[char]
    end.join
  end
end
