class Phrase

  def initialize(phrase)
    @phrase = phrase
  end
  def word_count
    @phrase.downcase!
    words_array = @phrase.split(/[^a-zA-Z0-9']/)
   freqs = Hash.new(0)
    words_array.each do |word| freqs[word] += 1
    end
    freqs.delete("")
    freqs
  end

end
