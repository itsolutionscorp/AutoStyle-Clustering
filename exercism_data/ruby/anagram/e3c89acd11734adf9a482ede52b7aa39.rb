class Anagram
  def initialize(key_phrase)
    @key_phrase = key_phrase.downcase
    @key_counts = letter_counts(@key_phrase)
  end

  def match(phrases_to_test)
    phrases_to_test.each_with_object([]) do |tested_phrase,matches|
      if is_anagram_of_key_string?(tested_phrase.downcase) 
        matches.push(tested_phrase)
      end
    end
  end

  def letter_counts(input_string)
    input_string.chars.each_with_object(Hash.new(0)) do |c,hash|
      hash[c] += 1
    end
  end

  private
    def is_anagram_of_key_string?(tested_phrase)
        tested_counts = letter_counts(tested_phrase)
        tested_phrase != @key_phrase and tested_counts.eql?(@key_counts) 
    end
end
