class Phrase
  attr_accessor :phrase_text

  def initialize(phrase_text)
    @phrase_text = phrase_text
  end

  def normalized_text
    phrase_text.downcase.gsub(/[^a-z0-9\s]/, ' ')
  end

  def word_count
    words = normalized_text.split(' ')
    count_hash = Hash.new(0)
    words.each { |w| count_hash[w] += 1 }
    count_hash
  end
end
