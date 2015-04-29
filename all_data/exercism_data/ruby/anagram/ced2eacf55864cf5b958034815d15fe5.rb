class Anagram
  def initialize(key_string)
    @key_string = key_string.downcase
    @key_hash = string_to_char_hash(@key_string)
  end

  def match(strings_to_test)
    strings_to_test.each_with_object([]) do |tested_string,matches|
      tested_string.downcase!
      tested_hash = string_to_char_hash(tested_string)
      if tested_hash.eql?(@key_hash) and tested_string != @key_string 
        matches.push(tested_string)
      end
    end
  end

  def string_to_char_hash(input_string)
    input_string.chars.each_with_object(Hash.new(0)) do |c,hash|
      hash[c] += 1
    end
  end
end
