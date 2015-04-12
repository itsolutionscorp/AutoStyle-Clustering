class Cipher
  ALPHABET = ('a'..'z').to_a

  attr_reader :key, :key_chars
  def initialize(key)
    @key = key || randomkey
    validate_key
  end

  def encode(plaintext)
    transform(plaintext, :+)
  end

  def decode(cipher)
    transform(cipher, :-)
  end

private
  def transform(text, action)
    kc = key_chars
    text.chars.map {|ch| ALPHABET[shift_index(ch, kc.resume, &action)] }.join
  end

  def key_chars
    Fiber.new {@key.chars.cycle {|k| Fiber.yield k }}
  end

  def shift_index(ch, k, &action)
    action.call(position(ch), position(k)) % ALPHABET.size
  end

  def position(ch)
    ALPHABET.index ch
  end

  def randomkey
    100.times.map {ALPHABET.sample}.join
  end

  def validate_key
    raise ArgumentError if key.match /\A[^a-z]*\z/
  end
end