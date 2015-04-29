class Phrase

  attr_reader :words

  def initialize(words)
    @words = words
  end

  def word_count
    Hash.new(0).tap do |word_count|
      find_each_words(words) do |word|
        word_count[word] += 1
      end
    end
  end

  def find_each_words(words)
    words.scan(/(\w+)/) do |match|
      yield normalize_match(match)
    end
  end

  def normalize_match(match)
    match[0].downcase
  end
end
