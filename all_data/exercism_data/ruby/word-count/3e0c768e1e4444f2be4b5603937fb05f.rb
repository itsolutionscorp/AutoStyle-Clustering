class Phrase < Struct.new(:phrase)
	
  def word_count
    words.each_with_object({}) { |word, result| result.merge! word => words.count(word) }
  end

  private

  def words
    phrase.downcase.scan(/\w+/)
  end

end
