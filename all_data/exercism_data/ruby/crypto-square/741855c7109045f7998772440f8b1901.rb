class Crypto
  def initialize (str)
    @str = str
  end
  def normalize_plaintext
    @str.gsub(/[^a-zA-Z0-9]/, "").downcase
  end
  def size
    Math.sqrt(normalize_plaintext.size).ceil
  end
  def plaintext_segments
    normalize_plaintext.chars.each_slice(size).map(&:join)
  end
  def ciphertext
    first, *others = plaintext_segments.map(&:chars)
    first.zip(*others).map(&:join).join
  end
  def normalize_ciphertext
    ciphertext.chars.each_slice(5).map(&:join).join(" ")
  end
end
