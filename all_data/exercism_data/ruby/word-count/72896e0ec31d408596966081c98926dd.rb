class Phrase < Struct.new(:phrase)
	
  def word_count
    words.each_with_object({}) { |word, result| result[word] = words.count(word) }
  end

  private

  def words
    phrase.downcase.scan(/\w+/)
  end

end
