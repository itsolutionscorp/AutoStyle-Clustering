class Cipher
  ALPHABET = ('a'..'z').to_a

  attr_reader :key
  def initialize(key)
    @key = key || randomkey
    validate_key
  end

  def randomkey
    rk = ''
    100.times { rk << ALPHABET.sample }
    rk
  end

  def encode(plaintext)
    transform(plaintext, &:+)
  end

  def decode(cipher)
    transform(cipher, &:-)
  end

  def position(ch)
    ALPHABET.index ch
  end

  def transform(text, &action)
    txt = text.chars
    xform = ''
    key.chars.cycle do |k|
      break unless (ch = txt.shift)
      xform << ALPHABET[shift_ndx(ch, k, &action)]
    end
    xform
  end

  def shift_ndx(ch, k, &action)
    action.call(position(ch), position(k))%ALPHABET.size
  end

  def validate_key
    raise ArgumentError if key.match /\A[^a-z]*\z/
  end
end
