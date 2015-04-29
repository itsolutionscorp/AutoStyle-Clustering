class Cipher
  LETTER_MAP = ('a'..'z').each_with_index.with_object({}) do |(letter, index), map|
    map[letter] = index
  end

  NUMBER_MAP = LETTER_MAP.invert

  attr_reader :key

  def initialize(key = generate_key)
    raise ArgumentError if key.empty? || key =~ /[^a-z]/

    @key = key
  end

  def encode(plaintext)
    plaintext.each_char.with_index.with_object('') do |(char, index), result|
      encode_index = (LETTER_MAP[char] + LETTER_MAP[key[index]]) % 26
      result << NUMBER_MAP[encode_index]
    end
  end

  def decode(result)
    result.each_char.with_index.with_object('') do |(char, index), plaintext|
      decode_index = (LETTER_MAP[char] - LETTER_MAP[key[index]]) % 26
      plaintext << NUMBER_MAP[decode_index]
    end
  end

  private

  def generate_key
    Array.new(100) { NUMBER_MAP[rand(26)] }.join
  end
end
