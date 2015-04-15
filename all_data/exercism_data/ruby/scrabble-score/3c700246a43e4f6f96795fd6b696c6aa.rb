class Scrabble

  attr_reader :word

  def initialize word
    @word = word.to_s.downcase
  end

  def self.score word
    new(word).score  
  end

  def letter_points
    { 'a' => 1,
      'b' => 3,
      'c' => 3,
      'd' => 2,
      'e' => 1,
      'f' => 4,
      'g' => 2,
      'h' => 4,
      'i' => 1,
      'j' => 8,
      'k' => 5,
      'l' => 1,
      'm' => 3,
      'n' => 1,
      'o' => 1,
      'p' => 3,
      'q' => 10,
      'r' => 1,
      's' => 1,
      't' => 1,
      'u' => 1,
      'v' => 4,
      'w' => 4,
      'x' => 8,
      'y' => 4,
      'z' => 10
    }
  end

  def score(word = @word)
    return letter_points[@word] if letter?
    add_points
  end

  def split_into_letters
    @word.downcase.chars
  end

  def add_points
    letters = split_into_letters
    letters.inject(0){|memo,letter| memo += letter_points.fetch(letter,0)}
  end

  def letter?
    letter_points.has_key?(@word)
  end

end
