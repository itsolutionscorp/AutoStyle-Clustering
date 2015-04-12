class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    find_words(normalize(@phrase)).inject(Hash.new(0)) do |wordsMap, word|
      wordsMap[word] += 1
      wordsMap
    end
  end

  private

  def normalize phrase
    remove_invalid_characters phrase.downcase.strip
  end

  def find_words phrase
    phrase.scan(/['\w]+/)
  end

  def remove_invalid_characters phrase
    phrase.gsub(Regexp.new('[^a-z0-9,\' ]', Regexp::IGNORECASE), '')
  end
end
