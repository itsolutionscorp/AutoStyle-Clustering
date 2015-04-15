require 'pry'

## SKIP SKIP SKIP

class String
  def to_a
    chars.to_a
  end
end

class Cipher
  def self.valid_cipher_key?(key)
    !(key.empty? || key.match(/[^a-z]+/))
  end

  attr_reader :key
  def initialize(key=nil)
    @key = key || default_key
    raise ArgumentError unless self.class.valid_cipher_key?(@key)
  end

  def encode(plaintext)
    plaintext.to_a.collect do |character|
      plaintext_characters_to_cipher_text_characters[character]
    end.join
  end

  def decode(encrypted_text)
    encrypted_text
  end

  private

  def plaintext_characters_to_cipher_text_characters
    plaintext_characters = ('a'..'z').to_a
    key_characters = key.to_a
    keys_to_values = plaintext_characters.zip(key_characters)
    Hash[keys_to_values]
  end

  def default_key
    'aaaaaaaaaaaaaaaaaaaaaaaaaa'
  end
end

c = Cipher.new('d')
encoded = c.encode('iamapandabear')
binding.pry
