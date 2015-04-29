class Phrase < String

  def word_count
    words.each_with_object(Hash.new(0)) { |word, word_count| word_count[word] += 1 }
  end

  def words
  	downcase.scan(/\w+/)
  end

end
