class Words
  attr_reader :words

  def initialize(sentence)
    @words = sentence_to_words(sentence)
  end

  def count
    counts = {}
    word_finder(@words, counts)
    counts
  end

  private

  def sentence_to_words(sentence)
    sentence.downcase.split(/\W+/)
  end

  def word_finder(words, counts)
    words.each do |word|
      if counts.include?(word)
        counts[word] += 1
      else
        counts[word] = 1
      end
    end
  end
end
