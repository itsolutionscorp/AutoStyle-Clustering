class Cipher
  attr_reader :key

  def initialize(key = nil)
    @key = validate key
  end

  def encode(plain_text)
    construct(plain_text) { encode_shifts }
  end

  def decode(encrypted_text)
    construct(encrypted_text) { decode_shifts }
  end

private
  def validate(key)
    key ||= generate
    raise ArgumentError unless key =~ /^[[:lower:]]+$/
    key
  end

  def generate
    [*"a".."z"].sample(100).join
  end

  def construct(text)
    text.chars.zip(yield).map { |char, shift| relative(char, shift) }.join
  end

  def encode_shifts
    @key.each_char.map { |c| position(c) }
  end

  def decode_shifts
    encode_shifts.map { |i| -i }
  end

  def relative(char, shift)
    ((position(char) + shift) % 26 + 97).chr
  end

  def position(char)
    char.ord - "a".ord
  end
end
