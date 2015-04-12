class Phrase 

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, hsh| hsh[word] += 1 }
  end

  private
  def words
    @phrase.scan(/\w+/)
  end
end
