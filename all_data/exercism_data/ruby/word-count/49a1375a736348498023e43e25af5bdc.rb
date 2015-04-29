class Phrase
  attr_reader :words

  def initialize(text)
    @words = text.downcase.gsub(/[^[:alnum:]]/, ' ').split
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, h|
      h[word] += 1
    end
  end
end
