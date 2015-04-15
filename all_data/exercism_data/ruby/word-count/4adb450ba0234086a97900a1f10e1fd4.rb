class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    counts = Hash.new { |hash, key| hash[key] = 0 }
    words_from_sentence.each_with_object(counts) do
      |word, hash| hash[word] += 1
    end
  end

  private

  def normalized_sentence
    @sentence.downcase
  end

  def words_from_sentence
    normalized_sentence.scan(/\w+[\'\w]*/)
  end
end
