class Scrabble
  def initialize(word_to_score)
    @word_to_score = word_to_score
  end

  def score
    score = 0
    unless @word_to_score == nil
      @word_to_score.downcase!
      @word_to_score.scan(/./) do |letter|
        score += score_letter(letter)
      end
    end
    score
  end

  def score_letter(letter)
    letter_score = 0
    case
      when letter[/(a|e|i|o|u|l|n|r|s|t)/]
        letter_score += 1
      when letter[/(b|c|m|p)/]
        letter_score += 3
      when letter[/(f|h|v|w|y)/]
        letter_score += 4
      when letter[/(k)/]
        letter_score += 5
      when letter[/(q|z)/]
        letter_score += 10
    end
    letter_score
  end
end
