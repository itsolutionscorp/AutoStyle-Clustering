class Scrabble
  attr_accessor :word

  def initialize(word)
    @word = word
  end

  def score
    self.class.score(word)
  end

  def self.score(word)
    count = 0
    unless word.nil?
      word.split("").map do |letter|
        count += letter_lookup(letter)
      end
    end
    count
  end

  def self.letter_lookup(letter)
    case letter.downcase
    when "a","e","i","o","u","l","n","r","s","t"
      1
    when "d","g"
      2
    when "b","c","m","p"
      3
    when "f","h","v","w","y"
      4
    when "k"
      5
    when "j","x"
      8
    when "q","z"
      10
    else
      0
    end
  end

end
