class Atbash
  ALPHABET = 'abcdefghijklmnopqrstuvwxyz'
  def self.encode(text)
    new(text).encode
  end

  attr_reader :text
  def initialize(text)
    @text = normalize text
  end

  def encode
    cipher = text.tr(ALPHABET, ALPHABET.reverse)
    blockformat cipher
  end

private
  def blockformat(text)
    text.scan(/.{1,5}/).join(' ')
  end

  def normalize(text)
    text.downcase.gsub(/[^a-z0-9]/,'')
  end
end 
