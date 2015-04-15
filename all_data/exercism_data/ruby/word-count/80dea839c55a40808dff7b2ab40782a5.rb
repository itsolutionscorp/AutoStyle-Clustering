class Phrase
  attr_reader :words

  def initialize input
    self.words = input
  end

  def word_count
    @word_count ||= words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  private

  def words= input
    @words = extract_words input.downcase
  end

  def extract_words words
    words.scan /\w+/
  end
end
