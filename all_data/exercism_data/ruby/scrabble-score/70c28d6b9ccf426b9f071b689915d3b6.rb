class Scrabble
  def initialize(s)
    @scores = self.class.load_scores
    @s = s
  end

  def score
    return 0 if @s.nil?
    @s.downcase.chars.map { |c| @scores[c] }.reduce(0, :+)
  end

  def self.score(s)
    self.new(s).score
  end

  private

  def self.load_scores
    File.read('README.md')[/^Letter\s+Value$(.*)```/m, 1].each_line.with_object(Hash.new(0)) do |line, scores|
      split = line.delete(',').split
      value = split.pop.to_i
      split.each { |letter| scores[letter.downcase] = value }
    end
  end
end
