class Phrase < String

  def word_count
    words.each_with_object(Hash.new(0)){|word, count| count[word] += 1}
  end

  private

  def words
    downcase.split(/\W+/)
  end

end
