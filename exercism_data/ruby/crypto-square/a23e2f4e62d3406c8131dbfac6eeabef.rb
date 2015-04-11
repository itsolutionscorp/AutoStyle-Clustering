class Crypto
  def initialize(text)
    @text = text
  end

  def normalize_plaintext
    text.gsub(/\W/, '').downcase
  end

  def size
    Math.sqrt(normalize_plaintext.size).ceil
  end

  def plaintext_segments
    normalize_plaintext.split(/(\w{#{size}})/).delete_if(&:empty?)
  end

  def ciphertext
    segments = plaintext_segments.map(&:chars)
    first_segment = segments.shift
    first_segment.zip(*segments).join
  end

  def normalize_ciphertext
    ciphertext.unpack("a#{plaintext_segments.size}" * size).join(' ')
  end

  private

  attr_reader :text
end
