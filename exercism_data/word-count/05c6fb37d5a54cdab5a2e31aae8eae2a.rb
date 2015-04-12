class Phrase
  def initialize(phrase)
    @words = WordList.new(parse(phrase))
  end

  def word_count
    words.count
  end

  private

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

  def words
    @words
  end
end

class WordList
  def initialize(words)
    @words = words
  end

  def count
    count_map = Hash.new(0)

    words.each do |word|
      count_map[word] += 1
    end

    count_map
  end

  private

  def words
    @words
  end
end
