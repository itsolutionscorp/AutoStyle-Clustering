class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase.scan(/[a-z0-9]+/)
  end

  def word_count
    @phrase.inject({}) do |dictionary, word|
      dictionary.merge({word => dictionary[word].to_i + 1})
    end
  end

end
