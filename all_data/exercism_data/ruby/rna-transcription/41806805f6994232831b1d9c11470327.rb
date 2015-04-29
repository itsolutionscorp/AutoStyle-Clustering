class Complement

  def self.of_dna(item)

    item.gsub(/[CGTA]/, 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U')

  end

  def self.of_rna(item)
    item.gsub(/[CGUA]/, 'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T')
  end

end
