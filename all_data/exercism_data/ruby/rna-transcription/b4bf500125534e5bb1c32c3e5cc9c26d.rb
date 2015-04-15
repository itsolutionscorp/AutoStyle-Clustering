class Complement
  def self.of_dna(in_str)
    in_str.gsub(/[GTAC]/, 'G'=>'C','T'=>'A','A'=>'U','C'=>'G',)
  end
  def self.of_rna(in_str)
    in_str.gsub(/[GUAC]/, 'G'=>'C','U'=>'A','A'=>'T','C'=>'G')
  end
end
