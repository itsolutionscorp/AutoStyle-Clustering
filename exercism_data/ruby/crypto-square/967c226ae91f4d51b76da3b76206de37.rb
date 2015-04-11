class Crypto
  def initialize(text)
    @plaintext = text
  end

  def normalize_plaintext
    @plaintext.downcase.gsub(/[^a-z1-9]/, '')
  end

  def size
    @size ||= (normalize_plaintext.size ** 0.5).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def matrix
    m = plaintext_segments.map { |seg| seg.scan(/./) }
    if (pad = size - m.last.size) > 0
      m[-1] += [nil] * pad
    end
    m
  end

  def ciphertext(delimiter = nil)
    matrix.transpose.flatten.compact.join
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,5}/).join(' ')
  end
end
