class Crypto
  def initialize(plaintext)
    @plaintext = plaintext
  end

  def normalize_plaintext
    @plaintext.downcase.gsub(/[^0-9a-z]/i, '')
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    plaintext = normalize_plaintext
    @segment_length = self.size
    plaintext.chars.each_slice(@segment_length).map(&:join).to_a
  end

  def ciphertext
    full = []
    plaintext_segments.each { |seg| full << seg.chars }
    (@segment_length - full.last.length).times { full.last << " " }
    full.transpose.flatten.join.gsub(/\s+/, "")
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,5}/).join(' ')
  end
end
