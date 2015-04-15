class Anagram
  def initialize(key_string)
    @key_string = key_string.downcase
    @key_hash = string_to_char_hash(@key_string)
  end

  def match(strings_to_test)
    strings_to_test.each_with_object([]) do |tested_string,matches|
      if string_is_anagram_of_key_string?(tested_string.downcase) 
        matches.push(tested_string)
      end
    end
  end

  def string_to_char_hash(input_string)
    input_string.chars.each_with_object(Hash.new(0)) do |c,hash|
      hash[c] += 1
    end
  end

  private
    def string_is_anagram_of_key_string?(tested_string)
        tested_hash = string_to_char_hash(tested_string)
        tested_string != @key_string and tested_hash.eql?(@key_hash) 
    end
end
