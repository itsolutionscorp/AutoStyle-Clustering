class Crypto

  def initialize plain_text
    @plain_text = plain_text
  end

  def normalize_plaintext
    @plain_text.downcase.gsub(/[^0-9a-z]/, '')
  end

  def plaintext_segments
    chunk(normalize_plaintext, size)
  end

  def ciphertext
    plaintext_segments.map do |segment|
      segment.chars.fill '', segment.size...size
    end.transpose.flatten.join
  end

  def normalize_ciphertext
    chunk(ciphertext, 5).join ' '
  end

  def size
    Math.sqrt(normalize_plaintext.size).ceil
  end

  private

  def chunk s, size
    s.scan(/.{1,#{size}}/)
  end
end
