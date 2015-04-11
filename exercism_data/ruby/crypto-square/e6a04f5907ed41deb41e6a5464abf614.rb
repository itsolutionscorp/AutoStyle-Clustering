class Crypto
  def initialize(message)
    @message = message
  end

  def normalize_plaintext
    @message.gsub(/\W/, "").downcase
  end

  def normalize_ciphertext
    slice_size = size > 2 ? size - 1 : size
    ciphertext.chars.each_slice(slice_size).map { |slice| slice.join }.join(" ")
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.chars.each_slice(size).map { |slice| slice.join }
  end

  def ciphertext
    plaintext_segments.map { |segment| pad(segment.chars, size, nil) }.transpose.join
  end

  private

  def pad(array, size, with)
    return array unless array.length < size
    array.fill(with, array.length, size - array.length)
  end
end
