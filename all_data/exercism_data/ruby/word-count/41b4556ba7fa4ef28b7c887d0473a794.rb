class Phrase < String
  def word_count
    normalized_words.each_with_object({}) { |word, count|
      count[word] = count[word].to_i + 1
    }
  end

  private 

  def normalized_words
    downcase.scan(/[\w\d]+/)
  end
end
