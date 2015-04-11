class Complement

  DNA_MAP = { 'A' => 'U', 'G' => 'C', 'T' => 'A', 'C' => 'G' }
  RNA_MAP = DNA_MAP.invert

  def self.of_dna(r)
   map_complement r, DNA_MAP
  end

  def self.of_rna(r)
   map_complement r, RNA_MAP
  end

  def self.map_complement(c, by)
    c.chars.map { |x| by[x] }.join
  end
end
