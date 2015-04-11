class Cipher
  ALPHABET = ("a".."z").to_a
  DEFAULT_KEY_LENGTH = 100

  attr_reader :key

  def initialize(key)
    @key = key || generate_key
    validate_key
  end

  def encode(plaintext)
    code(plaintext, 1)
  end

  def decode(ciphertext)
    code(ciphertext, -1)
  end

  private

  def code(text, direction)
    text.chars.zip(key.chars).map { |text_char, key_char|
      steps = ALPHABET.index(key_char) * direction
      index = (ALPHABET.index(text_char) + steps) % ALPHABET.length
      ALPHABET[index]
    }.join
  end

  def generate_key
    DEFAULT_KEY_LENGTH.times.map { ALPHABET.sample }.join
  end

  def validate_key
    unless key =~ /\A[a-z]+\z/
      raise ArgumentError, "Key must be all lower case letters"
    end
  end
end
