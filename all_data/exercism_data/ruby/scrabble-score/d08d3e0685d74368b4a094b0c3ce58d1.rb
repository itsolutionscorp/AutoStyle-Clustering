class Scrabble
  POINTS = { a: 1, e: 1, i: 1, o: 1, u: 1, l: 1, n: 1, r: 1, s: 1, t: 1, d: 2, g: 2, b: 3,
             c: 3, m: 3, p: 3, f: 4, h: 4, v: 4, w: 4, y: 4, k: 5, j: 8, x: 8, q: 10, z: 10 }.freeze

  def initialize(word)
    @word = word.to_s.strip.downcase
  end

  def self.score(word)
    Scrabble.new(word).score
  end

  def score
    @word.each_char.inject(0) { |a, e| a + POINTS.fetch(e.to_sym) }
  end
end
__END__
I'll implement double/triple scoring later.
