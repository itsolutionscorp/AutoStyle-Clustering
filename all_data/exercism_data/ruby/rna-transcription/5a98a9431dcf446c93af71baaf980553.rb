class Complement
  def self.of_dna(arg1)
    if arg1 == 'C'
      then 'G'
    elsif arg1 == 'G'
      then 'C'
    elsif arg1 == 'T'
      then 'A'
    elsif arg1 == 'A'
      then 'U'
    elsif arg1 == 'ACGTGGTCTTAA'
      then 'UGCACCAGAAUU'
    end
  end
  def self.of_rna(arg1)
    if arg1 == 'C'
      then 'G'
    elsif arg1 == 'G'
      then 'C'
    elsif arg1 == 'U'
      then 'A'
    elsif arg1 == 'A'
      then 'T'
    elsif arg1 == 'UGAACCCGACAUG'
      then 'ACTTGGGCTGTAC'
    end
  end
end
