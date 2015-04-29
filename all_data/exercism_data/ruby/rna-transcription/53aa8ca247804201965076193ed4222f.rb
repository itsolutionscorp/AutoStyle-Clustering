class Complement
  def self.of_dna rna
    map rna, @mapping
  end

  def self.of_rna dna
    map dna, @mapping.invert
  end

  private
  @mapping = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }

  def self.map str, mapping
    str.chars.map { |s| mapping[s] }.join
  end
end
