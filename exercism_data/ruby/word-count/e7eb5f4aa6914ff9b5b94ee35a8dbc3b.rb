class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.scan(/\w+/)
  end

  def word_count
    count = Hash.new(0)
    phrase.each do |key|
      count[key.downcase] += 1
    end
    count
  end
end
