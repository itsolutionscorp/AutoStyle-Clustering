class Phrase
  attr_reader :phrase, :words

  def initialize(phrase)
    @phrase = phrase
    @words = phrase.downcase.gsub(',', ' ').gsub(/[^a-z0-9'\s]/, '').split(' ')
  end

  def word_count
    json_word_count
  end

protected
  def json_word_count
    words.each.inject({}) do |result, word|
      if result[word].nil?
        result[word] = 1
      else
        result[word] += 1
      end
      result
    end
  end
end
