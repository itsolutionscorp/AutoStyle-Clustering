class Cipher
  attr_reader :key

  ALPHABET = ("a".."z").to_a

  def initialize(key)
    validate_key!(key) if key
    @key = key || generate_key
  end

  def encode(plaintext)
    transliterate(plaintext, :+)
  end

  def decode(encrypted)
    transliterate(encrypted, :-)
  end

  private

  def validate_key!(key)
    unless key =~ /\A[a-z]+\z/
      raise ArgumentError, "invalid key #{key.inspect}"
    end
  end

  def generate_key
    (ALPHABET * 5).shuffle.join
  end

  def transliterate(from, op)
    from.chars.zip(key.chars.cycle).map do |from, to|
      cipher_char(from, to, op)
    end.join
  end

  # Ugly :/
  def cipher_char(from, to, op)
    diff = (ALPHABET.index(from).send(op, ALPHABET.index(to))) % ALPHABET.size
    ALPHABET.at(diff)
  end
end
