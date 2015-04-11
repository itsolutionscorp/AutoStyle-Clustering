class Phrase < String

  def word_count
    words.inject(Hash.new(0)){|words, word| words[word] += 1; words}
  end

  private

  def words
    remove_punctuation.downcase.split(" ")
  end

  def remove_punctuation
    gsub(/\W+/," ")
  end
end
