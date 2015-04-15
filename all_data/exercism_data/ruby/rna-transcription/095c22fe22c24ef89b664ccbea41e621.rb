class Complement
  @@translations = {'C'=>'G',
                    'G'=>'C', 'T'=>'A', 'A'=>'U'}
  def self.of_dna(dna_string)
    result = ""
    dna_string.split("").each do |c|
      result += @@translations[c]
    end
     result
  end

  def self.of_rna(rna_string)
    result = ""
    rna_translations = @@translations.invert
    rna_string.split("").each do |c|
      result += rna_translations[c]
    end
     result
  end
end
