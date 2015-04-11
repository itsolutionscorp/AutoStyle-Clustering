class Crypto
  def initialize(string)
    @string = string.downcase.gsub(/[^a-z0-9]/,"")
  end
  def normalize_plaintext
    @string
  end
  def size
    (normalize_plaintext.length ** (0.5)).ceil
  end
  def plaintext_segments
    normalize_plaintext.each_char.each_slice(size).map(&:join)
  end
  def ciphertext
    arrays = plaintext_segments.map { |string| string.each_char.to_a}
    arrays[0].zip(*arrays[1..-1]).map(&:compact).flatten.join
  end
  def normalize_ciphertext
    ciphertext.each_char.each_slice(5).map(&:join).join(" ")
  end
end
