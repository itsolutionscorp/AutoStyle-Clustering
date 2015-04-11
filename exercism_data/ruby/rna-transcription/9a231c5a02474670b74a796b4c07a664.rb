class Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(strand)
    map_chars(strand, DNA_TO_RNA)
  end

  def self.of_rna(strand)
    map_chars(strand, RNA_TO_DNA)
  end

private

  def self.map_chars(strand, table)
    strand.each_char.map {|char| table[char]}.join
  end
end
