class Cipher
  attr_reader :key

  ALPHABET = [*'a'..'z']

  def initialize(key = nil)
    raise ArgumentError unless !key || key.match(/[a-z]+/)

    @key = key || ALPHABET.sample(100).join
  end

  def encode data
    shift data, true
  end

  def decode data
    shift data, false
  end

  private

  def shift data, encode
    data.chars.map.with_index do |c, i|
      id = ALPHABET.index(c)
      offset = ALPHABET.index(key[i]) * (encode ? 1 : -1)
      shifted_id = (id + offset) % ALPHABET.length
      ALPHABET[shifted_id]
    end.join
  end
end
