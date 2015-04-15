class Crypto
  def initialize(plain)
    @plain = plain
  end

  def normalize_plaintext
    @plain.scan(/\w|\d/).join.downcase
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    arrays = plaintext_segments.map { |segment| segment.ljust(size).chars }
    arrays.transpose.join.delete(' ')
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,5}/).join(" ")
  end
end
