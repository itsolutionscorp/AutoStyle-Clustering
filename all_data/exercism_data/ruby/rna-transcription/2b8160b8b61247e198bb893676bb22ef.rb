class Complement



  def self.of_dna(x)
    x.gsub(/[ACTG]/, 'A' => 'U', 'C' => 'G', 'T' => 'A', 'G' => 'C')
  end

  def self.of_rna(x)
    x.gsub(/[ACUG]/, 'A' => 'T', 'C' => 'G', 'U' => 'A', 'G' => 'C')
  end

end
