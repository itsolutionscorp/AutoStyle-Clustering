class Complement

  def self.of_dna(arg1)
    raise ArgumentError if arg1.include?('U')
    arg1.gsub(/[GCTAU]/, 'G'=> 'C', 'C'=> 'G', 'T'=> 'A', 'A'=> 'U')
  end
  def self.of_rna(arg1)
    raise ArgumentError if arg1.include?('T')
    arg1.gsub(/[GCUAT]/, 'G'=> 'C', 'C'=> 'G', 'U'=> 'A', 'A'=> 'T')
  end

end
