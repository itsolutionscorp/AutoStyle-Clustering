class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(words)
    @words_list = words
    result_keys = []
    @words_list.each_with_index do |word, key|
      result_keys << key if is_a_match?(word)
    end
    parse_keys_list(result_keys)
  end

  private
  def is_a_match?(word)
    word_array(word) == key_array && @word.downcase != word.downcase
  end

  def unique_word_list
    @words_list.map { |w| w.downcase }.uniq
  end

  def key_array
    @word.downcase.split("").sort
  end

  def word_array(word)
    word.downcase.split("").sort
  end

  def parse_keys_list(result_keys)
    results = []
    @words_list.each_with_index { |w,i| results << w if result_keys.include?(i) }
    results
  end
end
