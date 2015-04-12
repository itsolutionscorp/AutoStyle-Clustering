class Words

  attr_reader :phrase

  def initialize phrase
    @phrase = phrase
  end

  def count
    word_counts = Hash.new(0)

    each_word do |word|
      word_counts[word] += 1
    end

    word_counts
  end

  def each_word
    normalized_words = phrase.downcase.scan(%r{\w+})
    normalized_words.each do |word|
      yield word
    end
  end
end
