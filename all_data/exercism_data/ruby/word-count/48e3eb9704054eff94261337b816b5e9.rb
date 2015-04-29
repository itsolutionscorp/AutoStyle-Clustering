class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    strip_phrase(@phrase)
      .split(' ')
      .each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end

  def strip_phrase phrase
    phrase.gsub(/[^0-9a-z,' ]/i, '')
      .gsub(',',' ')
      .downcase
  end
end
