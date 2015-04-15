class Scrabble

  def self.score word
    new(word).score
  end

  def initialize(word)
    @word = word.to_s.upcase
  end

  def score
    @word.chars.map do |letter| 
      letter_score letter
    end.reduce(&:+).to_i
  end

  private
  def letter_score(letter)
    if  %w(A E I O U L N R S T).include? letter
      1
    elsif %w(D G).include? letter
      2
    elsif %w(B C M P).include? letter
      3
    elsif %w(F H V Y).include? letter
      4
    elsif %w(K).include? letter
      5
    elsif %w(J X).include? letter
      8
    elsif %w(Q Z).include? letter
      10
    else
      0
    end
  end
end
