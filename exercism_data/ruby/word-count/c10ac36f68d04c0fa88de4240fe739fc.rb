class Phrase

  Punctuation = /[!&@$%^-_,;:\.\s]+/

  def initialize str
    @phrase = str
  end

  def word_count
    words.each.with_object(Hash.new(0)) do |word, freqs|
        freqs[word] += 1
    end
  end

  def words
    @phrase.downcase.split(Punctuation)
  end

end
