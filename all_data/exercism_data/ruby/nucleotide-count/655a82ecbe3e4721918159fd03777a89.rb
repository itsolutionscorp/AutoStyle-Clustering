class DNA

  def initialize(string)
    @chars = string.chars.each{ |letter| validate(letter) }
  end

  def nucleotide_counts
    DNA_LETTERS.inject({}) { |hash, letter| hash[letter] = count(letter); hash }
  end

  def count(letter)
    validate(letter)
    @chars.count { |char| letter == char }
  end

  private

  DNA_LETTERS = ["A", "T", "C", "G"]

  def is_uracil?(letter)
    letter == 'U'
  end

  def validate(letter)
    throw ArgumentError unless DNA_LETTERS.include?(letter) || is_uracil?(letter)
  end    
end
