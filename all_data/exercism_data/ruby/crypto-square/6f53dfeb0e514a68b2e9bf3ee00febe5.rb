class Crypto
  attr_reader :plain
  private :plain

  def initialize(plain)
    @plain = plain
  end

  def normalize_plaintext
    plain.gsub(/\W/, '').downcase
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    chunk(normalize_plaintext, size)
  end

  def ciphertext
    transpose(plaintext_segments).join
  end

  def normalize_ciphertext
    chunk(ciphertext, 5).join(' ')
  end

  private

  def transpose(segments)
    padding = "%-#{size}s"
    segments.map { |x| (padding % x).chars.map(&:strip) }.transpose.flatten
  end

  def chunk(text, size)
    text.scan(/\w{1,#{size}}/)
  end
end
