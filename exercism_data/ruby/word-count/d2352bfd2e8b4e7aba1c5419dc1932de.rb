class Phrase
  attr_reader :word_count
  @@WORD_BOUNDARY = /[^\'\da-zA-Z]+/

  def initialize text
    @words = text.downcase.split @@WORD_BOUNDARY
    @word_count = @words.each_with_object(Hash.new(0)) {|w, freq| freq[w] += 1 }
  end
end
