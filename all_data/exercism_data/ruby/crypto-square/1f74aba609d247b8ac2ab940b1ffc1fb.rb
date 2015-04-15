class Crypto
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
    normalize_plaintext.chars.each_slice(size).map(&:join)
  end

  def ciphertext
    size.times.map { |i|
      plaintext_segments.map { |segment| segment[i] }.join
    }.join
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(5).map(&:join).join(" ")
  end
end
