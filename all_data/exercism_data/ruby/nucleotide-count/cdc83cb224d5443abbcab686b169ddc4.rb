class DNA
  attr_reader :chars
  DNA_LETTERS = ["A", "T", "C", "G"]

  def initialize(string)
    @chars = string.chars.each{ |letter| validate(letter) }
  end

  def nucleotide_counts
    DNA_LETTERS.each_with_object({}) { |letter, hash| hash[letter] = count(letter) }
  end

  def count(letter)
    validate(letter)
    chars.count(letter)
  end

  private

  def is_uracil?(letter)
    letter == 'U'
  end

  def validate(letter)
    throw ArgumentError unless DNA_LETTERS.include?(letter) || is_uracil?(letter)
  end    
end
