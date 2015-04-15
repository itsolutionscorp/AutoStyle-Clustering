#!/usr/bin/env ruby

# Exercism 29
# Crypto Square

class Crypto

  attr_reader :plaintext

  def initialize(plaintext)
    @plaintext = plaintext
  end

  def normalize_plaintext
    plaintext.downcase.gsub(/\W/, '')
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    chunk(normalize_plaintext, size)
  end

  def ciphertext
    plaintext_segments.map { |x| x.split('').fill('', x.length...size) }.transpose.join
  end

  def normalize_ciphertext
    chunk(ciphertext, plaintext_segments.size).join(' ')
  end

  def chunk(string, size)
    string.scan(/.{1,#{size}}/)
  end

end
