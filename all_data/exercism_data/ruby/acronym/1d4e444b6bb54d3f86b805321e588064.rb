class Acronym
  def self.abbreviate(phrase)
    new(phrase).abbreviate
  end

  def initialize(phrase)
    @phrase = phrase
  end

  def abbreviate
    detect || build
  end

  private

  def detect
    @phrase.split(/\W+/).find(&acronym?)
  end

  def build
    @phrase.split(/(?=[A-Z])|\W+/).map(&:chars).map(&:first).join.upcase
  end

  def acronym?
    ->(word) { word.match(/\A[[:upper:]]+\z/) }
  end
end
