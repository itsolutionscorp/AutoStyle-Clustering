class Phrase
  attr_reader :word_count

  def initialize(sentence)
    words = sentence.downcase().split(%r{\W+})
    @word_count = words.inject({}) do |freq, word|
       freq[word] = freq[word] ? freq[word] + 1 : 1
       freq
    end
  end
end
