class Phrase
  def initialize(phrase)
    @words = parse(phrase)
  end

  def word_count
    WordList.new(words).count
  end

  private

  def parse(phrase)
    normalize(phrase).scan(/\w+/)
  end

  def normalize(subject)
    subject.downcase
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
    words.each_with_object(Hash.new(0)) do |word, count_map|
      count_map[word] += 1
    end
  end

  private

  def words
    @words
  end
end