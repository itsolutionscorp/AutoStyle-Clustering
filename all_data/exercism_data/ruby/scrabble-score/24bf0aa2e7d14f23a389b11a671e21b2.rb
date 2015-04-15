class Scrabble
  SCORE = { 1 => ["A", "E", "I", "O", "U", "L", "N", "R", "S", "T"],
            2 => ["D", "G"],
            3 => ["B", "C", "M", "P"],
            4 => ["F", "H", "V", "W", "Y"],
            5 => ["K"],
            8 => ["J", "X"],
           10 => ["Q", "Z"] }

  def initialize word
    @word = permitted_word(word).map(&:upcase)
  end

  def self.score word
    new(word).score
  end

  def score
    @word.empty? ? 0 : calculate_score
  end

  private

  def permitted_word word
    word.nil? ? [] : word.scan(/[[:alpha:]]/)
  end

  def calculate_score
    @word.map { |letter| score_for_letter?(letter) }.inject(:+)
  end

  def score_for_letter? letter
    SCORE.each_with_object([]) do |(key, values), score|
      match = values.select { |value| value == letter }
      score << key unless match.empty?
    end.first
  end
end
