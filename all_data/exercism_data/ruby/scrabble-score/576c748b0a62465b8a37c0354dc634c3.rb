class Scrabble
  module ThreequalsInclude
    def ===(other)
      include?(other)
    end
  end

  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @word = word.to_s.upcase
  end

  def score
    letters.map(&method(:points)).inject(0, :+)
  end

  private

  def letters
    @word.chars
  end

  def points(letter)
    case letter
    when in_group("AEIOULNRST") then 1
    when in_group("DG")         then 2
    when in_group("BCMP")       then 3
    when in_group("FHVWY")      then 4
    when in_group("K")          then 5
    when in_group("JX")         then 8
    when in_group("QZ")         then 10
    else 0
    end
  end

  def in_group(letters)
    letters.tap { |group| group.extend ThreequalsInclude }
  end
end
