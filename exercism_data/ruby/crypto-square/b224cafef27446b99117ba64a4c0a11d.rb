class String
  # I've needed to chunk a string up several times, I wish that this was
  # part of the ruby standard library
  def chunk(chunk_size)
    return [self] if chunk_size >= size
    scan(/.{1,#{chunk_size}}/)
  end

  # I've needed to cast a String as an array several times, I also wish
  # that this was part of the standard library
  def to_a
    chars.to_a
  end
end

class Array
  def normalized_length(length)
    length.times.collect { |i| self[i] }
  end
end

# The Exercism provided API didn't feel quite right to me.  I chose
# different method names because I think they better capture what each
# method is trying to do.  I also wanted to practice working with an
# interface that I didn't control while still providing a good
# experience.  This set of method aliases are factored out of the class
# so that, as an end user, I'm not even thinking of using the old names.
module Exercism
  module CryptoAPI
    def self.included(base)
      base.class_eval do
        alias_method :normalize_plaintext, :normalized_plaintext
        alias_method :size, :square_size
        alias_method :plaintext_segments, :normalized_plaintext_segments
        alias_method :normalize_ciphertext, :normalized_ciphertext
      end
    end
  end
end

class Crypto
  attr_reader :plaintext, :special_character_regex, :cipher_chunk_size

  def initialize(plaintext, options = {})
    @plaintext = plaintext.to_s
    @special_character_regex = options.fetch(:special_character_regex, 
                                             default_special_character_regex)
    @cipher_chunk_size = options.fetch(:cipher_chunk_size,
                                          default_cipher_chunk_size)
  end

  def normalized_plaintext
    @normalized_plaintext ||= plaintext.gsub(special_character_regex, '').downcase
  end

  def ciphertext
    normalized_cipher_character_block.transpose.flatten.join
  end

  def square_size
    Math.sqrt(normalized_plaintext.size).ceil
  end

  def normalized_plaintext_segments
    normalized_plaintext.chunk(square_size)
  end

  def normalized_ciphertext
    ciphertext.chunk(cipher_chunk_size).join(' ')
  end

  include Exercism::CryptoAPI

  private

  def normalized_cipher_character_block
    normalized_plaintext_segments.map do |characters|
      characters.to_a.normalized_length(square_size)
    end
  end

  def default_special_character_regex
    /[#\$%\^&,!\. ]/
  end

  def default_cipher_chunk_size
    5
  end
end
