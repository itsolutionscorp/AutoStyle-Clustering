class Phrase
  attr_reader :phrase, :word_count

  def initialize(phrase)
    @phrase = phrase
    @word_count = {}
  end

  def word_count
    return @word_count unless @word_count.empty?
    @phrase.downcase.scan(/[\w']+/).each do |word|
      if @word_count[word]
        @word_count[word] += 1
      else
        @word_count[word] = 1
      end
    end
    @word_count
  end
end
