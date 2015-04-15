class Scrabble
  @@scores = {
    1 => %w(a e i o u l n r s t),
    2 => %w(d b),
    3 => %w(b c m p),
    4 => %w(f h v w y),
    5 => %w(k),
    8 => %w(j x),
    10 => %w(q z)
  }


  def self.get_score_hash
    score_hash = {}
    @@scores.each do |score, letters|
      letters.each do |letter|
        score_hash[letter] = score
      end
    end
    score_hash
  end

  @@score_hash ||= self.get_score_hash

  def self.score(letter)
    new(letter).score
  end

  def initialize(letters)
    @letters = letters
  end

  def score
    return 0 unless @letters

    @letters.downcase.chars.reduce(0) do |score, letter|
      if @@score_hash[letter]
        score += @@score_hash[letter]
      else
        score += 0
      end
    end
  end
end
