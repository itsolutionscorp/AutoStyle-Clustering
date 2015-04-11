class Scrabble

  def initialize(word)
    @points = {
              %w(a e i o u l n r s t) => 1,
              %w(d g) => 2,
              %w(b c m p) => 3,
              %w(f h v w y) => 4,
              %w(k) => 5,
              %w(j x) => 8,
              %w(q z) => 10
              }
    word.nil? ? @word = '' : @word = word
  end

  def score
    @word.scan(/[a-zA-Z]/).map(&:downcase).inject(0) do |total, letter|
      @points.each do |k, v|
        total += v if k.include?(letter)
      end
      total
    end
  end

  def self.score(word)
    new(word).score
  end

end
