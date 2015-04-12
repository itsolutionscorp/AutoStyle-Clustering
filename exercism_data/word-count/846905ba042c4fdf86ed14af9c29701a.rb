class Phrase < String
  def word_count
    count = Hash.new(0)
    normalized_words.each do |word|
      count[word] += 1
    end
    return count
  end

  private 

  def normalized_words
    self.downcase.gsub(/[^\p{Alnum} ]/, ' ').split
  end
end
