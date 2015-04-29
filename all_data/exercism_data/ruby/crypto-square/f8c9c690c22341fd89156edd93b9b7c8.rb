class Crypto
  def initialize(d)
    @plain_text = d
  end

  def normalize_plaintext
    @plain_text.scan(/[A-Za-z0-9]/).join.downcase
  end

  def plaintext_segments
    normalize_plaintext.split('').each_slice(size).to_a.map(&:join)
  end

  def ciphertext
    [].tap do |x|
      size.times { |y| x << plaintext_segments.map { |z| z[y] } }
    end.flatten.join
  end

  def normalize_ciphertext
    ciphertext.split('').each_slice(size(:round)).to_a.map(&:join).join(' ')
  end

  def size(rounding = :ceil)
    Math.sqrt(normalize_plaintext.length).send(rounding)
  end
end
