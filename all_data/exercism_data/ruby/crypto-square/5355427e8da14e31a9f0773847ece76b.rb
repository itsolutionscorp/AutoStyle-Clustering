class Crypto
  def initialize text
    @text = text
  end

  def normalize_plaintext
    @normal ||= @text.downcase.gsub(/[^0-9a-z]/, '')
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    segments normalize_plaintext
  end

  def ciphertext
    text = ''
    size.times do |i|
      plaintext_segments.each do |segment|
        text << segment[i] if segment[i]
      end
    end
    text
  end

  def normalize_ciphertext
    segments(ciphertext).join(' ')
  end

  private

  def segments text
    segments = text.scan(/.{1,#{size}}/)
  end
end
