class Crypto
  def initialize data
    @data = data
    @scrubbed = clean_up_chars(data).downcase.chars
  end

  def normalize_plaintext
    @scrubbed.join
  end

  def size
    squarish_root @scrubbed.size
  end

  def plaintext_segments
    segments.map(&:join)
  end

  def ciphertext
    clean_up_chars make_cipher_text.join
  end

  def normalize_ciphertext
    x = make_cipher_text.map { |a| clean_up_chars a.join }.join
    offset = x.length < 4 ? 0 : 1
    y = x.chars.each_slice(squarish_root(x.size)-offset).to_a
    y.map(&:join).join(' ')
  end

  private

  def segments
    @scrubbed.each_slice(size).to_a
  end

  def clean_up_chars x
    x.gsub(/[^0-9a-zA-Z]/, '')
  end

  def squarish_root x
    (x**0.5).ceil
  end

  def clean_up_shortness s
    extra   = s.first.size - s.last.size
    padding = "@" * extra
    s.last.push *(padding.chars)
    s
  end

  def make_cipher_text
    clean = clean_up_shortness segments
    clean.transpose
  end
end
