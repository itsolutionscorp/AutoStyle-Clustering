class Words

  attr_reader :words

  def initialize phrase
    @words = normalize(phrase)
  end

  def count
    words.each_with_object(Hash.new(0)) do |word, word_counts|
      word_counts[word] += 1
    end
  end

  def normalize phrase
    phrase.downcase.scan(%r{\w+})
  end
end
