class Complement

  def self.of_dna(r)
   map_complement r, 'A' => 'U', 'G' => 'C', 'T' => 'A', 'C' => 'G'
  end

  def self.of_rna(r)
   map_complement r, 'U' => 'A', 'C' => 'G', 'A' => 'T', 'G' => 'C'
  end

  def self.map_complement(c, by)
    c.chars.map { |x| by[x] }.join
  end
end
