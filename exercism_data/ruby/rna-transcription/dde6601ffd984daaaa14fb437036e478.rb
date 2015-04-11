module Complement

  Rna_to_dna_values = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'}

  def self.of_dna(dna)
    self.translate(dna,Rna_to_dna_values.invert)
  end

  def self.of_rna(rna)
    self.translate(rna,Rna_to_dna_values)
  end

  def self.translate(input,dictionary)

    input.chars.map{ #for each character
        |c| dictionary[c] #return dictionary value
    }.join #then join to a single string

    #shamelessly stolen from leifg. Check his code. He's better.
  end

end
