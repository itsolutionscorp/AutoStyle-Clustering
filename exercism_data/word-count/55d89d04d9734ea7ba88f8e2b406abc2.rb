class Phrase
  attr_reader :phrase_to_count
  def initialize(test_phrase)
    @phrase_to_count = scrub(test_phrase)
  end
  
  def word_count
    words = parse_words_into_array(phrase_to_count)
    count_words_into_hash(words)
  end
  
  private
  
  def parse_words_into_array(phrase_to_parse)
    phrase_to_parse.split
  end
  
  def count_words_into_hash(word_array)
    word_array.inject({}) do |count_hash, word|
      count_hash[word] = count_hash[word].to_i + 1
      count_hash
    end
  end
  
  def scrub(input_string)
    String(input_string).downcase.gsub(/[^\w\s]/, '').strip
  end
end
