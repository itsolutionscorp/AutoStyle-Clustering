class Scrabble

  @@scores = Hash.new(0)
  { 1 => %w(a e i o u l n r s t),
    2 => %w(d g),
    3 => %w(b c m p),
    4 => %w(f h v w y),
    5 => %w(k),
    8 => %w(j x),
    10=> %w(q z) }.each do |s,a|
    a.each { |c| @@scores[c] = s }
  end
    @@scores.default = 0

  def initialize(w)
    if w.nil?
      @@word = ""
    else
      @@word = w
    end
  end

  def score
    a = @@word.chars.map { |c| @@scores[c.downcase] }
    if a.any?
      return a.inject(:+)
    else
      return 0
    end
  end

  def self.score(word)
    return Scrabble.new(word).score
  end

end
