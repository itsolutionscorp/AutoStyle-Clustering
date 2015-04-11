module Complement
  def self.of_dna(sequence)
    new_sequence = ""
    sequence.each_char do |c|
      new_sequence += self.replace_dna(c)
    end
    new_sequence
  end

  def self.of_rna(sequence)
    new_sequence = ""
    sequence.each_char do |c|
      new_sequence += self.replace_rna(c)
    end
    new_sequence
  end

  def self.replace_dna(c) 
    if c=='C'
      'G'
    elsif c=='G'
      'C'
    elsif c=='T'
      'A'
    elsif c=='A'
      'U'
    else 
      c
    end
  end

  def self.replace_rna(c)
    if c=='C'
      'G'
    elsif c=='G'
      'C'
    elsif c=='U'
      'A'
    elsif c=='A'
      'T'
    else 
      c
    end
  end
end
