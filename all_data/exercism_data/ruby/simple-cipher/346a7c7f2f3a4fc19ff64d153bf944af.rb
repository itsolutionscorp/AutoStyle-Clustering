class Cipher
  ALPHABET = ('a'..'z').to_a.join

  attr_reader :key

  def initialize(key = "dddddddddd")
    @key = validate_key(key)
  end

  def encode plaintext
    encoded_text = plaintext.chars.map.with_index do |char, index|
      ALPHABET[difference_of_chars_encode(char, index)]
    end
    encoded_text.join
  end

  def decode encoded_text
    decoded_text = encoded_text.chars.map.with_index do |char, index|
      ALPHABET[(difference_of_chars_decode(char, index))]
    end
    decoded_text.join
  end

  private

  def validate_key key
    case key
    when /^[^a-z]+$/
      raise ArgumentError
    when ""
      raise ArgumentError
    else
      key
    end
  end

  def alphabet_pos char
    ALPHABET.index(char)
  end

  def difference_of_chars_encode char, index
    new_char_index = alphabet_pos(char) + alphabet_pos(key[index])
    new_char_index > 25 ? new_char_index - 26 : new_char_index
  end

  def difference_of_chars_decode char, index
    new_char_index = alphabet_pos(char) - alphabet_pos(key[index])
    new_char_index > 25 ? new_char_index - 26 : new_char_index
  end
end
