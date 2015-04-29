class Phrase
  attr_reader :word_count

  def initialize(sentence)
    @word_count = frequencies to_words(sentence)
  end

  private

  def frequencies(list)
    list.each_with_object(Hash.new(0)) { |item, freq| freq[item] += 1 }
  end

  def to_words(sentence)
    sentence.downcase().split(%r{\W+})
  end
end
