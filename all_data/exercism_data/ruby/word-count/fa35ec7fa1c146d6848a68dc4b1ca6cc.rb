class Phrase

  attr_reader :word_count

  def initialize(text)
    @word_count = {}
    words = text.downcase.scan(/[\w']+/);
    words.each do |word|
      @word_count[word] = words.count(word) unless @word_count[word]
    end
  end
  
end
