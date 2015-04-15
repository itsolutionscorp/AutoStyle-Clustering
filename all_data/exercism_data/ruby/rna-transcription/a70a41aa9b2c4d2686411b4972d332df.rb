module Complement
  def self.of_dna(dna)
    dictionary = { #dna to rna values
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'}

    self.translate(dna,dictionary)
  end

  def self.of_rna(rna)
    dictionary = { #rna to dna values
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'}

    self.translate(rna,dictionary)
  end

  def self.translate(input,dictionary)

    input.chars.map{ #for each character
        |c| dictionary[c] #return dictionary value
    }.join #then join to a single string

    #shamelessly stolen from leifg. Check his code. He's better.
  end

end
