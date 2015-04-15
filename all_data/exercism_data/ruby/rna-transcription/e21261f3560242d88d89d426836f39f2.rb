class Complement
  @dna = {'G' =>'C', 'C' =>'G', 'T' =>'A', 'A' =>'U'}
  @rna = @dna.invert
  
  def self.of_dna a
    a.chars.map { |c| @dna[c] }.join
  end

  def self.of_rna a
    a.chars.map { |c| @rna[c] }.join
  end
end
