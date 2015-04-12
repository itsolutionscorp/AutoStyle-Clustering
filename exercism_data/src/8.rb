class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.downcase.scan(/\w+/)
  end

  def word_count
    count = Hash.new(0)
    phrase.each do |key|
      count[key] += 1
    end
    count
  end
end
