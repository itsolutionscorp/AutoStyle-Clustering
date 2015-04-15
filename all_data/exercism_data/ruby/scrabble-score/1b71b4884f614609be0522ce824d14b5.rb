class Scrabble

  def initialize(word)
    @word = word
    @scrabble_hash = {
      "a" => 1,
      "b" => 3,
      "c" => 3,
      "d" => 2,
      "e" => 1,
      "f" => 4,
      "g" => 2,
      "h" => 4,
      "i" => 1,
      "j" => 8,
      "k" => 5,
      "l" => 1,
      "m" => 3,
      "n" => 1,
      "o" => 1,
      "p" => 3,
      "q" => 10,
      "r" => 1,
      "s" => 1,
      "t" => 1,
      "u" => 1,
      "v" => 4,
      "w" => 4,
      "x" => 8,
      "y" => 4,
      "z" => 10,
    }
  end

  def score
    scrabble_score = 0

    if @word == "" || @word == " \t\n" || @word == nil
      scrabble_score
    else
      word_array = @word.split(//)
      word_array.each do |elements|
        elements.downcase!
      end

      @scrabble_hash.each do |letter, points|
        word_array.each do |compare|
          if compare == letter
            scrabble_score += points
          end
        end
      end
      scrabble_score
    end
  end

  def self.score(word)
   new(word).score
  end

end
