class Complement
  def self.of_dna(dna)
    dictionary = { #dna to rna values
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'}

    self.translate(dna,dictionary)
  end

  def self.of_rna(rna)
    dictionary = { #dna to rna values
                    'C' => 'G',
                   'G' => 'C',
                   'A' => 'T',
                   'U' => 'A'}

    self.translate(rna,dictionary)
  end

  def self.translate(input,dictionary)
    output = ''

    input.each_char do |i|
      output += dictionary[i]
    end
    output
  end

end
