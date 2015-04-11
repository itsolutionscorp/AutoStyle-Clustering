class Scrabble
  SCORES = {
    %w(a e i o u l n r s t) => 1,
    %w(d g)                 => 2,
    %w(b c m p)             => 3,
    %w(f h v w y)           => 4,
    %w(k)                   => 5,
    %w(j x)                 => 8,
    %w(q z)                 => 10
  }

  def self.score(input)
    new(input).score
  end

  def initialize(input)
    @letters = letters input
  end

  def score
    @letters.reduce(0) { |a, e| a + letter_score(e) }
  end

  private

  def letters(user_input)
    user_input.to_s.downcase.scan(/[a-z]/)
  end

  def letter_score(char)
    score_db[char]
  end

  def score_db
    @scores ||= build_score_db
  end

  def build_score_db
    SCORES.each_with_object({}) { |(char_list, score), a|
      char_list.each { |char| a[char] = score }
    }
  end
end
