class Complement
  def self.of(type, sequence)
    lookup = {
      common: {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'U' => 'A'
      },
      dna: {
        'A' => 'U'
      },
      rna: {
        'A' => 'T'
      }
    }
    complement = ''
    sequence.each_char do |char|
      complement += lookup[:common][char] ? lookup[:common][char] : lookup[type][char]
    end
    complement
  end

  def self.of_dna(sequence)
    self.of(:dna, sequence)
  end

  def self.of_rna(sequence)
    self.of(:rna, sequence)
  end
end
