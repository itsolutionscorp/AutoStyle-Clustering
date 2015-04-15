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
      shift_by = position_of(key_char) * direction
      position = position_of(text_char) + shift_by
      letter_at(position)
    }.join
  end

  def position_of(letter)
    ALPHABET.index(letter)
  end

  def letter_at(position)
    ALPHABET[position % ALPHABET.length]
  end

  def generate_key
    DEFAULT_KEY_LENGTH.times.map { ALPHABET.sample }.join
  end

  def validate_key
    unless key =~ /\A#{Regexp.union *ALPHABET}+\z/
      raise ArgumentError, "Key must be all lower case letters"
    end
  end
end
