class Cipher
  ALPHABET = 'abcdefghijklmnopqrstuvwxyz'.freeze

  attr_accessor :key

  def initialize(key = random_string)
    fail ArgumentError, 'key must be lower case alpha' if key[/[^a-z]|\A\z/]
    @key = key
  end

  def encode(string)
    string.each_char.zip(key.each_char.cycle).map do |c, k|
      ALPHABET[(ALPHABET.index(c) + ALPHABET.index(k)) % ALPHABET.length]
    end.join
  end

  def decode(string)
    string.each_char.zip(key.each_char.cycle).map do |c, k|
      ALPHABET[ALPHABET.index(c) - ALPHABET.index(k)]
    end.join
  end

  private

  def random_character
    ALPHABET[rand(ALPHABET.length)]
  end

  def random_string(length = 100)
    Array.new(length) { random_character }.join
  end
end
