# crypto.rb
class Crypto
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def normalize_plaintext
    message.downcase.gsub(/[^a-z0-9]/, '')
  end

  def size
    (normalize_plaintext.size**0.5).ceil
  end

  def plaintext_segments
    chunk(normalize_plaintext, size)
  end

  def ciphertext
    transpose_slices.flatten.join
  end

  def normalize_ciphertext
    chunk(ciphertext, plaintext_segments.size).join(' ')
  end

  private

  def chunk(string, length)
    string.scan(/.{1,#{length}}/)
  end

  def matrix
    plaintext_segments.map(&:chars)
  end

  def transpose_slices
    matrix.first.zip(*matrix[1..-1])
  end
end
