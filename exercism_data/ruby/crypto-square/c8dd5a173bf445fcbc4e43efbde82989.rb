class Crypto
  def initialize(str)
    @data = str
  end

  def normalize_plaintext
    @data.gsub(/[^a-zA-Z0-9]/, '').downcase
  end

  def plaintext_segments
    normalize_plaintext.chunks(size)
  end

  def ciphertext
    column = ->(i) { plaintext_segments.map { |seg| seg[i].to_s }.join }
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
  def chunks size
    self.chars.each_slice(size).map(&:join)
  end
end
