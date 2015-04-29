class Scrabble
  def self.score(word)
    new(word).score
  end

  def initialize(word)
    @word = word.to_s.upcase
  end

  def score
    letters.inject(0) { |total, letter| total + points(letter) }
  end

  private

  def letters
    @word.chars.to_a
  end

  def points(letter)
    case letter
    when group("AEIOULNRST") then 1
    when group("DG")         then 2
    when group("BCMP")       then 3
    when group("FHVWY")      then 4
    when group("K")          then 5
    when group("JX")         then 8
    when group("QZ")         then 10
    else 0
    end
  end

  def group(letters)
    letters.dup.tap do |set|
      set.define_singleton_method(:===) { |letter| include?(letter) }
    end
  end
end
