class Scrabble
  attr_accessor :word

  def initialize word
    self.word = word
    @points = {
      a: 1,
      b: 3,
      c: 3,
      d: 2,
      e: 1,
      f: 4,
      g: 2,
      h: 4,
      i: 1,
      j: 8,
      k: 5,
      l: 1,
      m: 3,
      n: 1,
      o: 1,
      p: 3,
      q: 10,
      r: 1,
      s: 1,
      t: 1,
      u: 1,
      v: 4,
      w: 4,
      x: 8,
      y: 4,
      z: 10
    }
    @points.default = 0
  end

  def score
    if self.word.nil? then 0 else self.word.downcase.chars.inject(0){ |highscore, char| highscore + @points[char.to_sym] } end
    # these don't work for some reason
    # self.word.chars.inject(0){ |highscore, char| highscore + @points[char.to_sym] } unless self.word.nil? else 0
    # 0 if self.word.nil? else self.word.chars.inject(0){ |highscore, char| highscore + @points[char.to_sym] }
  end

  def self.score word
    self.new(word).score
  end
end
