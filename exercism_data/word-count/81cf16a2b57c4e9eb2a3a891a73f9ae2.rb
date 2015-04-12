class WordExtractor
  def extract_words(text)
    words = text.split(/[\s,:!@\$\^\%\.\&]+/)

    words.map{ |w| w.downcase }
  end
end

class Phrase
  attr_reader :word_count

  def initialize(text)
    @extractor = WordExtractor.new
    @word_count = count_words(text)
  end

  def count_words(text)
    words = @extractor.extract_words(text)

    word_count = Hash.new(0)
    words.each { |w| word_count[w] += 1 }

    word_count
  end
end
