require "pry"

class Crypto
  attr_reader :normalize_plaintext

  def initialize(text)
    @normalize_plaintext = text.downcase.scan(/\w+/).join
  end

  def size
    Math.sqrt(normalize_plaintext.size).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan( Regexp.new(".{1,#{size}}") )
  end

  def ciphertext
    square.reduce do |transpose, row|
      transpose.zip(row)
    end.flatten.join
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,5}/).join(" ")
  end

  private

  def square
    plaintext_segments.map { |segment| segment.chars }
  end
end
