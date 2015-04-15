class Phrase

  def initialize statement
    @statement = statement
  end

  def word_count
    @statement.downcase.scan(/[\w']+/).inject({}) do |res, word| 
      res.has_key?(word) ? res[word] += 1 : res[word] = 1
      res
    end 
  end
end
