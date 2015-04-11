class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words.each_with_object({}) do |word, hsh|
      hsh[word] = hsh.fetch(word, 0) + 1
    end
  end

  def words
    @phrase.split(/(?:[.,:!$%^&@-]|\s)+/)
           .map(&:downcase)
  end

end
