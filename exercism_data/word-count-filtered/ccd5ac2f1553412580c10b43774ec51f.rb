class Phrase
  def initialize sentence
    @words = sentence.downcase.split /[^\'\da-zA-Z]+/
    @histogram = nil
  end

  def word_count
    @histogram ||= @words.each_with_object(Hash.new(0)) {|w, h| h[w] += 1 }
  end
end
