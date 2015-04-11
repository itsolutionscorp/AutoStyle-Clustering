class Complement
  @dna = {'G' =>'C', 'C' =>'G', 'T' =>'A', 'A' =>'U'}
  @rna = @dna.invert
  
  def self.of_dna a
    a.split('').map { |c| @dna[c] }.join
  end

  def self.of_rna a
    a.split('').map { |c| @rna[c] }.join
  end
end
