class Phrase

  Punctuation = /[!&@$%^-_,;:\.\s]+/

  def initialize str
    @phrase = str
  end

  def word_count
    counts = Hash.new { |hash, key| hash[key] = 0 }
    @phrase.downcase.split(Punctuation).each do |word|
        counts[word] += 1
    end
    counts
  end

end
