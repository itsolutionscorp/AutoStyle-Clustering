class Scrabble

  def initialize(word)
    @word = word
    @score_hash = {
        "a" => 1, "b" => 3, "c" => 3, "d" => 2, "e" => 1,
        "f" => 4, "g" => 2, "h" => 4, "i" => 1, "j" => 8,
        "k" => 5, "l" => 1, "m" => 3, "n" => 1, "o" => 1,
        "p" => 3, "q" => 10, "r" => 1, "s" => 1, "t" => 1,
        "u" => 1, "v" => 4, "w" => 4, "x" => 8, "y" => 4,
        "z" => 10
    }
  end

  def score
    score_total = 0

    if @word == "" || @word == " \t\n" || @word == nil
      score_total
    elsif letter_array = @word.downcase.split(//)
      letter_array.each do |match|
        @score_hash.each do |letter, score|
          if match == letter
            score_total += score

          end
        end
      end
    end
    score_total
  end
  def self.score(string)
    new(string).score
  end
end
