class Complement
  @dna = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  @rna = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }
  class << self
    def of_dna(sequence)
      sequence.split('').map do |letter|
        @dna[letter]
      end.join('')
    end

    def of_rna(sequence)
      sequence.split('').map do |letter|
        @rna[letter]
      end.join('')
    end
  end
end
