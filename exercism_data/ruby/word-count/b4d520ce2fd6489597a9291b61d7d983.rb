class Phrase

  def initialize text
    @words = text.downcase.scan /[\'\w+]+/
  end

  def word_count
    @wc ||= @words.each_with_object(Hash.new(0)) { |w, freq| freq[w] += 1 }
  end
end
