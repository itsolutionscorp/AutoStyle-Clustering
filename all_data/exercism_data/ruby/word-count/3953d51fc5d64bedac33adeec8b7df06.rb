class Phrase
  attr_accessor :word_count
  def initialize(phrase)
    @phrase = phrase.downcase.gsub(/[,:.!&@$%^]/, ' ').gsub(/[ \t]+/, ' ')
    @word_count = @phrase.split.inject({}) do |result, word|
      result[word] ||=0
      result[word] += 1
      result
    end
  end
end
