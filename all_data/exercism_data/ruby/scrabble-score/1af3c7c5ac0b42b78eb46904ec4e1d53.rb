class Scrabble
  def initialize(word)
    @word = word.to_s.strip.downcase
  end

  def score
    if @word.empty?
      0
    else
      @word.chars.each.inject(0) { |sum, c| sum + letter_scores[c] }
    end
  end

  def self.score(word)
    self.new(word).score
  end

  private

  def letter_scores
    letters = ['aeioulnrst', 'dg', 'bcmp', 'fhvwy', 'k', 'jx', 'qz']
    values = [1, 2, 3, 4, 5, 8, 10]

    @scores ||= letters.each_with_index.each_with_object({}) do |(l, i), h|
      h.merge!(generate_score(l.chars, values[i]))
    end
  end

  def generate_score(letters, points)
    letter_values = letters.product([points])
    Hash[letter_values]
  end
end
