# scrabble.rb
# Scrabble score

class Scrabble
  @@scorefor = Hash.new(0)
  { 1 => %w(a e i o u l n r s t),
    2 => %w(d g),
    3 => %w(b c m p),
    4 => %w(f h v w y),
    5 => %w(k),
    8 => %w(j x),
    10=> %w(q z) }.each do |s,a|
    a.each { |c| @@scorefor[c] = s }
  end
  
  def initialize(word)
    @word = word
  end
  
  def score
    Scrabble.score(@word)
  end
  
  def self.score(word)
    i=0
    word.to_s.downcase.chars do |c|
      i += @@scorefor[c]
    end
    return i
  end
end
