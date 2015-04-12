class Phrase
  attr_reader :phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    counts = Hash.new(0)
    parse_phrase.each do |word|
      counts[word] += 1
    end
    counts
  end

  def parse_phrase
    phrase.downcase.scan(/\w+/)
  end
end
