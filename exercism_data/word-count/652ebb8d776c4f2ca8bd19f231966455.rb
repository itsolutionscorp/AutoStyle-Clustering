class SpecialCharacterRemover

  def self.call(str)
    str.gsub(/\W+/, ' ')
  end

end

class PhraseWordDivider

  def self.call(phrase)
    phrase.split(/\s+/).map do |word|
      word.downcase
    end
  end

end

class Phrase
  attr_reader :words

  def initialize(phrase)
    @words = PhraseWordDivider.call(
                                    SpecialCharacterRemover.call(phrase))
  end

  def word_count
    words.each_with_object({}) do |word, hash|
      hash[word] = words.count(word)
    end
  end

end
