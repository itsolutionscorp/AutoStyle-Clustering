class DNA
  attr_reader :string
  
  def initialize(string)
    @string = string.chars
    @string.each do |nucleotide|
      validate(nucleotide)
    end
  end

  def count(letter)
    validate(letter)
    matches = string.find_all do |nucleotide|
      nucleotide == letter
    end
    matches.length
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'T' => count('T'),
      'C' => count('C'),
      'G' => count('G')
    }
  end

  def validate(letter)
    raise ArgumentError unless valid_letters.include?(letter)
  end

  def valid_letters
    valid_letters = ['A', 'T', 'C', 'G']
  end
end
