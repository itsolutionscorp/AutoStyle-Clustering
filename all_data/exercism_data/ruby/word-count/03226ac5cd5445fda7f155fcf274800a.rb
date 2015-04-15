class Words
  attr_reader :words

  def initialize(sentence)
    @words = sentence_to_words(sentence)
  end

  def count
    counts = Hash.new(0)
    each_word{ |word| counts[word] += 1}
    counts
  end

  private

  def sentence_to_words(sentence)
    sentence.downcase.split(/\W+/)
  end

  def each_word
    words.each do |word|
      yield(word)
    end
  end
end
