class Cipher

  def initialize(key = nil)
    @key = valid_key(key) if key
  end

  def encode(string)
    transform(string, :+)
  end

  def decode(string)
    transform(string, :-)
  end

  def key
    @key ||= random_key
  end

  private

  ALPHABET_INDEX = Hash[("a".."z").map.with_index {|l, i| [l, i] }]
  INDEX_ALPHABET = Hash[ALPHABET_INDEX.to_a.map(&:reverse)]

  def transform(string, zip_method)
    string.chars.zip(key_cycle).each_with_object("") do |(a, b), str|
      str << alpha((index(a).send(zip_method, index(b))) % 26)
    end
  end

  def random_key
    100.times.map { alpha rand(26) }.join
  end

  def valid_key(key)
    raise ArgumentError if key.empty? || key.match(/[^a-z]/)
    key
  end

  def alpha(index)
    INDEX_ALPHABET[index]
  end

  def index(alpha)
    ALPHABET_INDEX[alpha]
  end

  def key_cycle
    key.chars.lazy.cycle
  end
end
