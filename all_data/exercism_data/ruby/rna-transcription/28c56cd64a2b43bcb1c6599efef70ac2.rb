class Complement
  DNA = ["C", "G", "T", "A"]
  RNA = ["G", "C", "A", "U"]

  def self.of_dna(string)
    convert(string, DNA, RNA)
  end

  def self.of_rna(string)
    convert(string, RNA, DNA)
  end

  def self.convert(string, array1, array2)
    string.chars.map do |char|
      array2[array1.index(char)]
    end.join('')
  end

end
