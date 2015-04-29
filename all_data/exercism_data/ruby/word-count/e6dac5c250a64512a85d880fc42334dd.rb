class Phrase
  attr_reader :phrase_to_count
  def initialize(test_phrase)
    @phrase_to_count = scrub(test_phrase)
  end
  
  def word_count
    count_words(break_phrase_into_words(phrase_to_count))
  end
  
  private
  
  def break_phrase_into_words(phrase_to_parse)
    phrase_to_parse.split
  end
  
  def count_words(word_array)
    word_array.each_with_object(Hash.new(0)) { |word, count_hash| count_hash[word] += 1 }
  end
  
  def scrub(input_string)
    String(input_string).downcase.gsub(/[^\w\s]/, '').strip
  end
end
