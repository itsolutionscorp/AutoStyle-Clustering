class Crypto
  def initialize(message)
    @message = message
  end

  def normalize_plaintext
    @message.downcase.gsub(/[^a-zA-Z0-9]/, "")
  end

  def size
    Math.sqrt(normalize_plaintext.size).ceil
  end

  def plaintext_segments
    normalize_plaintext.chars.each_slice(size).map(&:join)
  end

  def ciphertext
    plaintext_matrix.transpose.flatten.join
  end

  def plaintext_matrix
    plaintext_matrix = plaintext_segments.map(&:chars)

    fill_size = size - plaintext_matrix.last.size
    plaintext_matrix.last.concat([nil] * fill_size) if fill_size > 0

    plaintext_matrix
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(size).map(&:join).join(" ")
  end
end
