class Phrase < String

  def word_count
    result = {}
    result.tap do result
      array_of_words.each do |word|
        word.downcase!
        result[word] = result[word] ? result[word] + 1 : 1
      end
    end
  end

  private

  def array_of_words
    split(/\W+/)
  end

end
