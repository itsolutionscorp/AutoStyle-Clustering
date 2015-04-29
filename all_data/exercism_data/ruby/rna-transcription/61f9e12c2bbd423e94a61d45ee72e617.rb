class Complement
  # keys are dna, values are rna
  SUBSTITUTES = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(dna)
    substitute(dna)
  end

  def self.of_rna(rna)
    substitute(rna, false)
  end

  def self.substitute(str, is_dna = true)
    str.chars.each_with_index do |str_char, idx|
      str[idx] = (is_dna ? SUBSTITUTES[str_char] : SUBSTITUTES.key(str_char))
    end
    str
  end
end
