class Crypto
  def initialize(plaintext)
    @plaintext = plaintext
  end

  def normalize_plaintext
    plaintext.gsub(/[^[:alnum:]]/, "").downcase
  end

  def size
    (normalize_plaintext.size ** 0.5).ceil
  end

  def plaintext_segments
    chunk_string(normalize_plaintext, size)
  end

  def ciphertext
    plaintext_matrix.transpose.join.delete(" ")
  end

  def normalize_ciphertext
    chunk_string(ciphertext, row_length).join(" ")
  end

  private

  attr_reader :plaintext

  def row_length
    plaintext_segments.size
  end

  def plaintext_matrix
    plaintext_segments.map { |segment| segment.ljust(size).chars }
  end

  def chunk_string(string, chunk_size)
    string.scan(/.{1,#{chunk_size}}/)
  end
end
