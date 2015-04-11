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
      steps = index(key_char) * direction
      shift(text_char, steps)
    }.join
  end

  def shift(letter, steps)
    index = index(letter) + steps
    index %= ALPHABET.length
    ALPHABET[index]
  end

  def index(letter)
    ALPHABET.index(letter)
  end

  def generate_key
    Array.new(DEFAULT_KEY_LENGTH) { ALPHABET.sample }.join
  end

  def validate_key
    unless key =~ /\A[a-z]+\z/
      raise ArgumentError, "Key must be all lower case letters"
    end
  end
end
