class Crypto
  CIPHERTEXT_SEGMENT_SIZE = 5

  def initialize(input)
    @input = input
  end

  def normalize_plaintext
    @input.gsub(/[[:punct:][:space:]]/, "").downcase
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    segments(normalize_plaintext, size)
  end

  def ciphertext
    size.times.map { |i|
      plaintext_segments.map { |segment| segment[i] }.join
    }.join
  end

  def normalize_ciphertext
    segments(ciphertext, CIPHERTEXT_SEGMENT_SIZE).join(" ")
  end

  private

  def segments(text, length)
    text.scan(/.{1,#{length}}/)
  end
end
