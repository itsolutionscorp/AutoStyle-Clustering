class Cipher
  ALPHABET = ('a'..'z').to_a

  attr_reader :key
  def initialize(key)
    @key = key || randomkey
    validate_key
  end

  def encode(plaintext)
    transform(plaintext, &:+)
  end

  def decode(cipher)
    transform(cipher, &:-)
  end

private
  def transform(text, &action)
    k = key_chars
    text.chars.inject('') do |xform, ch|
      xform << ALPHABET[shift_ndx(ch, k.resume, &action)]
    end
  end

  def key_chars
    Fiber.new {key.chars.cycle {|k| Fiber.yield k }}
  end

  def shift_ndx(ch, k, &action)
    action.call(position(ch), position(k))%ALPHABET.size
  end

  def position(ch)
    ALPHABET.index ch
  end

  def randomkey
    (1..100).inject('') {|rk,i| rk << ALPHABET.sample}
  end

  def validate_key
    raise ArgumentError if key.match /\A[^a-z]*\z/
  end
end
