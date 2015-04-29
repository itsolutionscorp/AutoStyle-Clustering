class Phrase
  def initialize(my_phrase)
    @phrase = my_phrase
  end
  def word_count
    words = get_words_from_phrase(@phrase)
    hash_number_of_words(words)
  end
  private
  def get_words_from_phrase(phrase)
    phrase.split(/[^a-zA-Z0-9']+/).map{|a| a.downcase}
  end
  def hash_number_of_words(word_table)
    hash = Hash.new(0)
    word_table.each do |word|
      hash[word] = hash[word]+1
    end
    hash
  end
end
