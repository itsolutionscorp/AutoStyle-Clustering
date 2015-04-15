class Cipher
  attr_reader :key
  ALPHABET = ('a'..'z').to_a

  def initialize(key = ('a' * 20))
    fail ArgumentError if key.empty? || key.match(/[A-Z0-9]/)
    @key = key
  end

  def encode str
    substitute str
  end

  def decode str
    substitute str, reverse: true
  end

  private

  def substitute str, options = {}
    str.chars.zip(key.chars).map do |str_c, key_c|
      cipher = ALPHABET.rotate(ALPHABET.index(key_c))
      tables = [cipher, ALPHABET]
      tables.reverse! if options[:reverse]
      tables[0][tables[1].index(str_c)]
    end.join
  end
end
