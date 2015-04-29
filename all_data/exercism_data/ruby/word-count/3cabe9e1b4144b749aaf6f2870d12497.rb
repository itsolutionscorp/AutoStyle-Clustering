class Phrase
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private

  def words
    word.scan(/\w+/).map &:downcase
  end
end
