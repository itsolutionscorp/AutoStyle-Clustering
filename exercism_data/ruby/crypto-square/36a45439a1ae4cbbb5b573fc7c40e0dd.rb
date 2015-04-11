class Crypto

  def initialize(plaintext)
    @plaintext = plaintext
  end

  def normalize_plaintext
    @plaintext.downcase.delete('^a-z0-9')
  end

  def size
    Math.sqrt(normalize_plaintext.size).ceil
  end

  def segments
    normalize_plaintext.chars.each_slice(size)
  end

  def plaintext_segments
    segments.map(&:join)
  end

  def ciphertext
    segments.first.zip(*segments.drop(1)).join
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(nct_size).map(&:join).join(' ')
  end

  def nct_size
    size < 4 ? 2 : size - 1
  end

end
