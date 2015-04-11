class Scrabble
  attr_reader :word

  SCORING = {
  1 => %w{A E I O U L N R S T},
  2 => %w{D G},
  3 => %w{B C M P},
  4 => %w{F H V W Y},
  5 => %w{K},
  8 => %w{J X},
  10 => %w{Q Z}
  }

  def initialize(word)
    @word = word
    @point_hash = transform(SCORING)
  end

  def score
    return 0 if word.nil? || word.strip.empty?
    word.downcase.each_char.map{|l| @point_hash[l]}.inject(&:+)
  end

  def transform(old)
    hsh = {}
    old.each do |k,v|
      hsh.merge!(Hash[v.map!(&:downcase).zip(Array.new(v.size, k))])
    end
    hsh
  end

  def self.score(word)
    self.new(word).score
  end
end
