class Crypto
  def initialize(plaintext)
    @plaintext = plaintext
  end

  def normalize_plaintext
    @plaintext.scan(/\w/).join.downcase
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.chars
      .each_slice(size).map(&:join)
  end

  def ciphertext
    size.times.map do |i|
      plaintext_segments.map do |segment|
        segment[i]
      end
    end.join
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(5)
      .map(&:join).join(' ')
  end

end
