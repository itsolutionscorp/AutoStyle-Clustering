class Complement
  @@translations = {'C'=>'G',
                    'G'=>'C', 'T'=>'A', 'A'=>'U'}
  def self.of_dna(dna_string)
    dna_string.chars.map { |c| @@translations[c]}.join
  end

  def self.of_rna(rna_string)
    rna_string.chars.map  {|c| @@translations.invert[c]}.join
  end
end
