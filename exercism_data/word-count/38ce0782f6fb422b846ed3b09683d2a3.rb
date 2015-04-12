class PhraseParser
  attr_reader :text
  def initialize(text)
    @text = text
  end

  def words
    text.split(non_word_pattern)
  end

  private
  def non_word_pattern
    /\W+/
  end
end

class DowncasingWordNormalizer
  def normalize(word)
    word.downcase
  end
end

class WordCounter
  attr_reader :word_counts
  def initialize
    @word_counts = Hash.new(0)
  end

  def count(word)
    word_counts[word] += 1
  end
end

class Phrase
  attr_reader :phrase_parser
  attr_reader :word_normalizer
  attr_reader :word_counter

  def initialize(text)
    @phrase_parser = PhraseParser.new(text)
    @word_normalizer = DowncasingWordNormalizer.new
    @word_counter = WordCounter.new
  end

  def word_count
    @word_counts ||= build_word_counts
  end

  private
  def build_word_counts
    phrase_parser.words.each do |word|
      normalized_word = word_normalizer.normalize(word)
      word_counter.count(normalized_word)
    end

    word_counter.word_counts
  end
end
