class Scrabble

def initialize(word)
  @word = word
  @score_hash = {
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
  output_score = 0
  if @word == "" || @word == " \t\n" || @word == nil
     output_score
  elsif
    letter_array = @word.split(//)
    letter_array.each do |x|
      x.downcase!
    end

      @score_hash.each do |letter,point|
        letter_array.each do |match|
          if match == letter
            output_score += point
          end
        end
      end
    end
    output_score
end
  def self.score(word)
    score_hash = {
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
    output_score = 0
    if word == "" || word == " \t\n" || word == nil
      output_score
    elsif
    letter_array = word.split(//)
      letter_array.each do |x|
        x.downcase!
      end

      score_hash.each do |letter,point|
        letter_array.each do |match|
          if match == letter
            output_score += point
          end
        end
      end
    end
    output_score
  end
end
