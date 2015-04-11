class Complement

  @@dna_map = ['G', 'C', 'T', 'A']
  @@rna_map = ['C', 'G', 'A', 'U']

  def self.of_dna(dna_strand)
    generate_complement(dna_strand, @@dna_map, @@rna_map)
  end

  def self.of_rna(rna_strand)
    generate_complement(rna_strand, @@rna_map, @@dna_map)
  end

  def self.generate_complement(strand, map_from, map_to)
    strand.chars.map { |nucl| map_to[map_from.index(nucl)] }.reduce(:+)
  end

end
