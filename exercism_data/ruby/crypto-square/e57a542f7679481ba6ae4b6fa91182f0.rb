class Crypto
  def initialize(text)
    @text = text
  end

  def normalize_plaintext
    @text.downcase.gsub(/[^a-z0-9]/, '')
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    plaintext_segments.map { |x| x.ljust(size).chars }.transpose.join.gsub(' ', '')
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,5}/).join(' ')
  end
end
