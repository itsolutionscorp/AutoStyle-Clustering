class Complement
  @to_rna = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  @to_dna = @to_rna.invert

  def self.of_dna(input)
    input.chars.map{|c| @to_rna[c]}.join
  end

  def self.of_rna(input)
    input.chars.map{|c| @to_dna[c]}.join
  end

end
