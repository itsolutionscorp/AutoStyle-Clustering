class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    tokens = @phrase.downcase.gsub(/[^a-z0-9]/, ' ').split
    word_count = {}
    tokens.each do |token|
      if word_count.has_key? token
        word_count[token] = word_count[token] + 1
      else
        word_count[token] = 1
      end
    end
    word_count
  end
end
