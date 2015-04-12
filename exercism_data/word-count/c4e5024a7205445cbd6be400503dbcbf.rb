class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    counts = {}
    extract_words_from_sentence.each do |word|
      if counts.has_key?(word)
        counts[word] += 1
      else
        counts[word] = 1
      end
    end
    counts
  end

  private

  def normalized_sentence
    @sentence.downcase
  end

  def extract_words_from_sentence
    normalized_sentence.scan(/\w+[\'\w]*/)
  end
end
