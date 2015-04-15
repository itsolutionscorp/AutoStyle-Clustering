class Cipher
  attr_reader :key

  ALPHABETS = ('a'..'z').to_a

  def initialize(key = nil)
    valid_key?(key) unless key.nil?

    @key = key || ALPHABETS.sample(100).join
  end

  def valid_key?(key)
    fail ArgumentError unless key.match(/^[a-z]+$/)
  end

  def encode(plaintext)
    plaintext.bytes.map.with_index do |byte, idx|
      byte_to_char(byte + key_offsets[idx])
    end.join
  end

  def decode(plaintext)
    plaintext.bytes.map.with_index do |byte, idx|
      byte_to_char(byte - key_offsets[idx])
    end.join
  end

  private

  def key_offsets
    @key_offsets ||= @key.bytes.map { |byte| byte - 'a'.ord }
  end

  def byte_to_char(byte)
    ord = (byte - 'a'.ord) % ALPHABETS.length + 'a'.ord
    ord.chr
  end
end
