class Phrase
  def initialize(phrase)
    @words = WordList.new(parse(phrase))
  end

  def word_count
    words.count
  end

  private

  attr_reader :words

  def parse(phrase)
    word_list = []

    phrase.scan(/\w+/) do |word|
      word_list << normalize(word)
    end

    word_list
  end

  def normalize(word)
    word.downcase
  end
end

class WordList
  def initialize(words)
    @words = words
  end

  def count
    words.each_with_object(Hash.new(0)) do |word, count_map|
      count_map[word] += 1
    end
  end

  private

  attr_reader :words
end
