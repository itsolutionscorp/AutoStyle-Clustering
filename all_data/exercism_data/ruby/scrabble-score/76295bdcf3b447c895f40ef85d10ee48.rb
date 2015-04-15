class Scrabble
  attr_reader :word

  VALUE_OF = {
    "a" =>  1, "b" =>  3, "c" =>  3, "d" =>  2, "e" =>  1, "f" =>  4,
    "g" =>  2, "h" =>  4, "i" =>  1, "j" =>  8, "k" =>  5, "l" =>  1,
    "m" =>  3, "n" =>  1, "o" =>  1, "p" =>  3, "q" => 10, "r" =>  1,
    "s" =>  1, "t" =>  1, "u" =>  1, "v" =>  4, "w" =>  4, "x" =>  8,
    "y" =>  4, "z" => 10, "?" =>  0
  }

  def initialize(word)
    @word = word
    @meta_class = class << self; self; end
  end

  def score
    @score ||= @meta_class.make_score(letters)
  end

  def letters
    @letters ||= @meta_class.get_letters(word)
  end

  def self.score(word)
    make_score get_letters word
  end

  private

  def self.get_letters(word)
    letter_matches = word && word.downcase.match(/[a-z]+/) || ["?"]
    letter_matches[0].strip.chars
  end

  def self.make_score(letters)
    letters.map{ |letter| VALUE_OF[letter] }.reduce(:+)
  end
end
