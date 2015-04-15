class Crypto
  attr_reader :plaintext
  def initialize plaintext
    @plaintext = plaintext
  end
  def normalize_plaintext
    @normalized_plaintext ||= plaintext.downcase.gsub(/\W/,'')
  end
  def size
    @size ||= Math.sqrt(normalize_plaintext.length).ceil
  end
  def plaintext_segments
    @plaintext_segments ||= normalize_plaintext.chars.each_slice(size).map(&:join)
  end
  def ciphertext
    @ciphertext ||= plaintext_segments.map{|string| string.ljust size}.map(&:chars).transpose.map(&:join).join.gsub(/\ /,'')
  end
  def normalize_ciphertext
    @normalized_ciphertext ||= ciphertext.chars.each_slice(5).map(&:join).join(' ')
  end
end
