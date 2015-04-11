class Scrabble
  def initialize(word)
    @word = word
    @value_hash = {"a" => 1, "e" => 1,
                   "i" => 1, "o" => 1,
                   "u" => 1, "l" => 1,
                   "n" => 1, "r" => 1,
                   "s" => 1, "t" => 1,
                   "d" => 2, "g" => 2,
                   "b" => 3, "c" => 3, "m" => 3, "p" => 3,
                   "f" => 4, "h" => 4, "v" => 4, "w" => 4, "y" => 4,
                   "k" => 5,
                   "j" => 8, "x" => 8,
                   "q" => 10, "z" => 10
    }
  end

  def score

    if @word == "" || @word == " \t\n" or @word == nil
      0
    else
      letter_array = @word.split ""
      score = 0

      letter_array.each do |letter|
        @value_hash.each do |key, value|
          if letter.downcase == key.downcase
            score += value.to_i
          end

        end

      end
      score
    end

  end

  def self.score(word)
    new(word).score
  end
end
