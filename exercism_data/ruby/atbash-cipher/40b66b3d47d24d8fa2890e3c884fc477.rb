class Atbash
  def self.encode(words)
    new(words).encode
  end

  attr_reader :words

  def initialize(words)
    @words = words
  end

  def encode
    convert(normalize(words))
  end

  private

  def convert(s)
    s.tr(alphabet, key).scan(/.{1,5}/).join(' ')
  end

  def normalize(s)
    s.downcase.gsub(/[^a-z0-9]/, '')
  end

  def alphabet
    'abcdefghijklmnopqrstuvwxyz'
  end

  def key
    alphabet.reverse
  end
end
