class Crypto
  attr_reader :string

  def initialize(string)
    @string = string
    normalize_plaintext
  end

  def normalize_plaintext
    @string = string.downcase.gsub(/\W/, '')
  end

  def size
    @size ||= Math.sqrt(string.length).ceil
  end

  def plaintext_matrix
    collection = string.chars
    padding = (size - collection.size % size) % size
    collection.concat([nil] * padding).each_slice(size).to_a
  end

  def plaintext_segments
    plaintext_matrix.map(&:join)
  end

  def ciphertext_matrix
    plaintext_matrix.transpose
  end

  def ciphertext
    ciphertext_matrix.map(&:join).join
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(5).map(&:join).join(" ")
  end
end
