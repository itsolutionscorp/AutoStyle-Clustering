class Scrabble
  SCORES = {
    1 => %w{a e i o u l n r s t},
    2 => %w{d g},
    3 => %w{b c m p},
    4 => %w{f h v w y},
    5 => %w{k},
    8 => %w{j x},
    10 => %w{q z},
  }

  def initialize(word)
    word ||= ''
    @word = word.gsub(/\W/, '').downcase
    @scores_lookup = Hash.new { |hash, key| hash[key] = 0 }

    @scores_lookup = SCORES.invert.each_with_object(@scores_lookup) do |(chars, score), memo|
      chars.each do |char|
        memo[char] = score
      end

    end
  end

  def score
    @word.chars.reduce(0) do |score, char|
      score += @scores_lookup[char]
    end
  end

  def self.score(word)
    new(word).score
  end
end
