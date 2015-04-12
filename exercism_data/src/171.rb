class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    arr = @phrase.downcase.gsub(/[^\w\']/,' ').split(' ')
    words = arr.uniq
    counts = {}
    words.each do |word|
      counts[word] = arr.count(word)
    end
    counts
  end

end
