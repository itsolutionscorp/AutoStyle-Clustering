class Cipher
  attr_reader :key

  def initialize(key = nil)
    @key = validate key
  end

  def encode(plain_text)
    plain_text.chars.zip(key.chars).inject("") do |result, (text_char, key_char)|
      result + encode_char(text_char, key_char)
    end
  end

  def decode(encrypted_text)
    encrypted_text.chars.zip(key.chars).inject("") do |result, (text_char, key_char)|
      result + decode_char(text_char, key_char)
    end
  end

private

  def validate(key)
    key = generate if key.nil?
    raise ArgumentError unless key =~ /^[[:lower:]]+$/
    key
  end

  def generate
    "aaaaaaaaaaaaaaaaaaaa"
  end

  def encode_char(text_char, key_char)
    ((position(text_char) + position(key_char)) % 26 + 97).chr
  end

  def decode_char(text_char, key_char)
    ((position(text_char) - position(key_char)) % 26 + 97).chr
  end

  def position(char)
    char.ord - "a".ord
  end
end
