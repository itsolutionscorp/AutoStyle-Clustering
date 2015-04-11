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
    phrase_to_parse.split(/\s+/)
  end
  
  def count_words_into_hash(word_array)
    word_hash = {}
    word_array.each{|word| word_hash[word] = word_hash[word].to_i + 1 }
    word_hash
  end
  
  def scrub(input_string)
    String(input_string).downcase.gsub(/[^\w\s]/, '').strip
  end
end
