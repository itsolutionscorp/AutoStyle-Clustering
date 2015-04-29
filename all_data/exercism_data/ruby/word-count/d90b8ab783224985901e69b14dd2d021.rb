class Phrase < String
  def word_count
    downcase.scan(/\w+/).each_with_object({}) do |word, hash|
      hash[word] = hash.fetch(word, 0) + 1
    end
  end
end
