class Phrase
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def word_count
    words.each_with_object(zeroed_hash) do |word, hash|
      hash[word] += 1
    end
  end

  private

  def zeroed_hash
    Hash.new(0)
  end

  def words
    word.scan(/\w+/).map &:downcase
  end
end
