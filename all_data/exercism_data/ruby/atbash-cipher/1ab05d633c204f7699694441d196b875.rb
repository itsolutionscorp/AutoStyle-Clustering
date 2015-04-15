class Atbash

  ALPHA = ('a'..'z').to_a + ('0'..'9').to_a
  CIPHER = ('a'..'z').to_a.reverse + ('0'..'9').to_a
  CIPHER_HASH = Hash[ALPHA.zip(CIPHER)]

  def self.clean_string_chars(input_string)
    input_string.downcase.gsub(/[^a-z0-9]+/,'').chars
  end

  def self.encipher(input_string)
    clean_string_chars(input_string).map { |char| CIPHER_HASH[char] }
  end

  def self.encode(input_string)
    encipher(input_string).join.scan(/.{1,5}/).join(' ')
  end

end
