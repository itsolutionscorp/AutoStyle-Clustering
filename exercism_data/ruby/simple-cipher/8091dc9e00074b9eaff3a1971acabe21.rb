require "forwardable"

class Alphabet
  extend Forwardable

  CHARS = ('a'..'z').to_a

  def_delegators :CHARS, :index

  def [](index)
    CHARS[index % CHARS.length]
  end

  def shift(char, count)
    self[index(char) + count]
  end

  def encrypt(char, key)
    shift(char, index(key))
  end

  def decrypt(char, key)
    shift(char, index(key)*-1)
  end
end

class Cipher
  ALPHABET = Alphabet.new

  attr_reader :key

  def initialize(key = generate_key)
    @key = key
    raise ArgumentError unless valid_key?
  end

  def encode(plaintext)
    each_char_and_key(plaintext) { |char, key| ALPHABET.encrypt(char, key) }
  end

  def decode(cipher)
    each_char_and_key(cipher)    { |char, key| ALPHABET.decrypt(char, key) }
  end

  private

  def each_char_and_key(message)
    pairs = char_pairs(message, strip_key_to_length_of(message))
    pairs.map { |(char, key)| yield char, key }.join
  end

  def strip_key_to_length_of(message)
    @key[0,message.length]
  end

  def char_pairs(one, other)
    [one, other].map(&:chars).map(&:to_a).transpose
  end

  def generate_key
    "akjbfkbasdkjbflkwjbd"
  end

  def valid_key?
    @key =~ /^[a-z]+$/
  end
end
