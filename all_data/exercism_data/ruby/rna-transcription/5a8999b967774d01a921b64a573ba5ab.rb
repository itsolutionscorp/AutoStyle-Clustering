class Complement
    @to_dna = {'C'=>'G', 'G'=>'C', 'T'=>'A',  'A'=>'U'}
    @to_rna = @to_dna.invert
  def self.of_dna(arg)
    arg.chars.map {|i|@to_dna[i]}.join
    end

  def self.of_rna(arg)
    arg.chars.map {|i|@to_rna[i]}.join
  end
end
