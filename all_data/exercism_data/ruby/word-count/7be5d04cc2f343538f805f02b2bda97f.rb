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
    word_array.inject(Hash.new(0)) do |count_hash, word|
      count_hash[word] += 1
      count_hash
    end
  end
  
  def scrub(input_string)
    String(input_string).downcase.gsub(/[^\w\s]/, '').strip
  end
end
