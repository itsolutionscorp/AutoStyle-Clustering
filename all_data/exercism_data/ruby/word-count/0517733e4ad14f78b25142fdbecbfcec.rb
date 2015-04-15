class Phrase

  Punctuation = /[!&@$%^-_,;:\.\s]+/

  def initialize str
    @phrase = str
  end

  def word_count
    freq = Hash.new { |hash, key| hash[key] = 0 }
    @phrase.downcase.split(Punctuation).each.with(freq) do |word, counts|
        counts[word] += 1
    end
  end

end
