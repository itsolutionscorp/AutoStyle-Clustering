class Nucleotide
  def self.from_dna(dna)
    letters = dna.chars
    fail ArgumentError.new("Invalid DNA #{dna}") unless letters.all?(&validate)
    new(letters)
  end

  def self.validate
    proc { |letter| %w(A C G T).include?(letter) }
  end

 attr_reader :letters

  def initialize(letters)
    @letters = letters
  end

  def count(n)
    letters.count(n)
  end

  def histogram
    {
      'A' => count('A'),
      'T' => count('T'),
      'C' => count('C'),
      'G' => count('G')
    }
  end
end
