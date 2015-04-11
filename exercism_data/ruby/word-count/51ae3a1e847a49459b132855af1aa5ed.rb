class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object({}) do |word, hash|
      hash[word] = words.count(word)
    end
  end

  private 
  def remove_special_characters
    phrase.gsub!(/\W+/, ' ')
  end

  def words
    remove_special_characters
    phrase.split(/\s+/).map do |word|
      word.downcase
    end
  end

end
