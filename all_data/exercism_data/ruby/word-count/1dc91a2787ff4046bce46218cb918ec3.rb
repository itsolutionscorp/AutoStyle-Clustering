class Phrase < String

  def word_count
    words.inject(Hash.new(0)){|count, word| count[word] += 1; count}
  end

  private

  def words
    downcase.split(/\W+/)
  end

end
