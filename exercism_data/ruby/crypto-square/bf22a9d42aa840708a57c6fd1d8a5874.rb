class Crypto
  def initialize(string)
    @string = string
  end

  def normalize_plaintext
    @normalized ||= string.scan(/\w+/).join.downcase
  end

  def normalize_ciphertext
    cipher_chars.each_slice(5).map(&:join).join(" ")
  end

  def size
    @size ||= Math.sqrt(normalize_plaintext.length).ceil
  end

  def ciphertext
    cipher_chars.join
  end

  def plaintext_segments
    padded_slices.map(&:join)
  end

  private

  attr_reader :string

  # :transpose requires the lengths of each subarray to match
  def padded_slices
    normalize_plaintext.chars.each_slice(size).to_a.tap do |*_, last|
      (size - last.size).times { last << nil } 
    end
  end

  def cipher_chars
    padded_slices.transpose.flatten.compact
  end
end
