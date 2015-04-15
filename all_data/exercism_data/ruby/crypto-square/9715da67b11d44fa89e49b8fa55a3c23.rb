class Crypto
  attr_reader :normalize_plaintext

  def initialize(plaintext)
    @normalize_plaintext = plaintext.gsub(/\W/, '').downcase
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/\w{1,#{size}}/)
  end

  def ciphertext
    ciphertext_segments.join
  end

  def normalize_ciphertext
    ciphertext.scan(/\w{1,5}/).join(" ")
  end

  private

  def plaintext_square
    square = plaintext_segments
    square.push(square.pop.ljust(size))
  end

  def ciphertext_segments
    plaintext_square.map!(&:chars).transpose.map! do |chars|
      chars.join.strip
    end
  end
end
