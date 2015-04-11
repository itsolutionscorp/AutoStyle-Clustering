class Crypto
  def initialize(str)
    @data = str
  end

  def normalize_plaintext
    @data.downcase.gsub(/[^[:alnum:]]/, '')
  end

  def plaintext_segments
    normalize_plaintext.chunks(size)
  end

  def ciphertext
    column = ->(i) { plaintext_segments.map { |seg| seg[i].to_s } }
    size.times.map { |i| column.(i) }.join
  end

  def normalize_ciphertext
    ciphertext.chunks(5).join(' ')
  end

  def size
    Math.sqrt(normalize_plaintext.size).ceil
  end
end

class String
  def chunks width
    self.chars.each_slice(width).map(&:join)
  end
end
