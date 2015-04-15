class Phrase
  def initialize(my_phrase)
    @phrase = my_phrase
  end
  def word_count
    words = get_words_from_phrase
    count_occurrence_of_words(words)
  end
  private
  def get_words_from_phrase
    @phrase.split(/[^a-zA-Z0-9']+/).map{|a| a.downcase}
  end
  def count_occurrence_of_words(word_table)
    word_table.each_with_object(Hash.new(0)) {|str, hash| hash[str] += 1}
  end
end
