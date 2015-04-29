class Scrabble
  VALUES = {
    1  => ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'],
    2  => ['D', 'G'],
    3  => ['B', 'C', 'M', 'P'],
    4  => ['F', 'H', 'V', 'W', 'Y'],
    5  => ['K'],
    8  => ['J', 'X'],
    10 => ['Q', 'Z']
  }

  def initialize(word)
    @word = word
  end

  def self.score(word)
    word.to_s.strip.split('').inject(0) { |score,char| score += value_of char }
  end

  def score
    self.class.score(@word)
  end

  def self.value_of(char)
    VALUES.each { |k,v| return k if v.include? char.upcase }
  end
end
