class Cipher
  def initialize(arg = nil)
    if !arg.nil?
      raise ArgumentError unless arg.match(/^[a-z]*$/) && !arg.empty?
      @key = arg
    else
      @key = (0...10).map { (97 + rand(26)).chr }.join
    end
    @numeric_key_chars = to_numeric_array(@key)
  end

  def key
    @key
  end

  def encode(plaintext)
    numeric_plaintext_chars = to_numeric_array(plaintext)
    numeric_encoded_chars = []

    numeric_plaintext_chars.each_with_index do |num, index|
      numeric_encoded_chars << (num + @numeric_key_chars[index % @key.length]) % 26
    end

    encoded_text = to_characters_string(numeric_encoded_chars)
    return encoded_text
  end

  def decode(ciphertext)
    numeric_ciphertext_chars = to_numeric_array(ciphertext)
    numeric_decoded_chars = []

    numeric_ciphertext_chars.each_with_index do |num, index|
      numeric_decoded_chars << (num - @numeric_key_chars[index % @key.length]) % 26
    end

    decoded_text = to_characters_string(numeric_decoded_chars)
    return decoded_text
  end

  private
    def to_numeric_array(string)
      string.split('').map { |c| c.ord - 97 }
    end

    def to_characters_string(array)
      array.map { |num| (num + 97).chr }.join
    end
end
