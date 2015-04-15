class Phrase
  def initialize(phr)
    @phrase = phr
  end

  def word_count
    Hash.new(0).tap { |h| @phrase.downcase.scan(/([a-z0-9']+\b)/).flatten.each { |x| h[x] += 1 } }
  end
end
