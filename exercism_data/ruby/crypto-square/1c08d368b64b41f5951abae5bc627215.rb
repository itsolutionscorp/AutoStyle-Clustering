class Crypto
  def initialize(message)
    self.raw_message = message
  end

  def normalize_plaintext
    @normalize_plaintext ||= raw_message.downcase.gsub /[^a-z1-9]/, ''
  end

  def size
    @size ||= Math.sqrt(normalize_plaintext.size).ceil
  end

  def plaintext_segments
    @plaintext_segments ||= segmentize(normalize_plaintext, size)
  end

  def ciphertext
    plaintext_segments.map { |segment| normalize_segment segment.chars.to_a, size }
                      .transpose
                      .map { |segment_chars| segment_chars.join "" }
                      .join("")
  end

  def normalize_ciphertext
    @normalize_ciphertext ||= segmentize(ciphertext, 5).join(" ")
  end

  private

  attr_accessor :raw_message

  def segmentize(string, n)
    string.chars
          .each_slice(n)
          .map { |segment_chars| segment_chars.join "" }
  end

  def normalize_segment(segment_chars, n)
    [*segment_chars, *Array.new(n - segment_chars.size, "")]
  end
end
