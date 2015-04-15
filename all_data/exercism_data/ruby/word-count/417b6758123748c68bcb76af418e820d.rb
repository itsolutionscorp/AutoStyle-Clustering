class Phrase

  def initialize(phrase)

    @phrase =  phrase.downcase.scan(/(\w+|\')/)
  end

  attr_reader :phrase

  def word_count
    h = Hash.new(0)
    phrase.each { |e| h[e] += 1 }
    h.map { |(k), v| [k, v] }.to_h
  end
end
