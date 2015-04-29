class Acronym
  def self.abbreviate(phrase)
    inception(phrase) || build(phrase)
  end

  def self.inception(phrase)
    phrase.split(/\W+/).find(&method(:acronym?))
  end

  def self.build(phrase)
    phrase.split(/(?=[A-Z])|\W+/).map(&:chars).map(&:first).join.upcase
  end

  def self.acronym?(word)
    word.match(/\A[[:upper:]]+\z/)
  end
end
