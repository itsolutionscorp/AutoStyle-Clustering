class Scrabble

  def self.score(w)
    Scrabble.new(w).score
  end

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def score
    total scrubbed_characters.collect {|character| character_value[character]}
  end

  private

  def total(values)
    return 0 if values.empty?
    values.reduce(:+)
  end

  def scrubbed_characters
    word.nil? ? [] : word.upcase.gsub(/\s/, '').split('')
  end

  def character_value
    {
      'A' => 1,
      'B' => 3,
      'C' => 3,
      'D' => 2,
      'E' => 1,
      'F' => 4,
      'G' => 2,
      'H' => 4,
      'I' => 1,
      'J' => 8,
      'K' => 5,
      'L' => 1,
      'M' => 3,
      'N' => 1,
      'O' => 1,
      'P' => 3,
      'Q' => 10,
      'R' => 1,
      'S' => 1,
      'T' => 1,
      'U' => 1,
      'V' => 4,
      'W' => 4,
      'X' => 8,
      'Y' => 4,
      'Z' => 10
    }
  end
end
