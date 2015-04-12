class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase.scan(/[[:alnum:]]+/)
  end

  def word_count
    @phrase.inject(Hash.new(0)) do |dictionary, word|
      dictionary.merge({word => dictionary[word] + 1})
    end
  end

end
