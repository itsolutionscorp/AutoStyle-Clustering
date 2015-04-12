class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count words
  end

  private 

  def words
    normalized_words.split(/[, ]/)
  end

  def normalized_words
    @phrase.gsub(/[:!&@$%^\.]/, "").downcase
  end

  def count words
    words.inject(Hash.new(0)) do |result, word| 
      result[word]+=1 unless word.empty? 
      result
    end
  end

end
