class Crypto

  def initialize(text)
    @text = text
  end

  def normalize_plaintext
    @text.gsub(/\s*\W*/, '').downcase
  end

  def size
    Math.sqrt(normalize_plaintext.size).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/\w{1,#{size}}/)
  end

  def ciphertext
    result = Array.new(size) { '' }
    plaintext_segments.each do |segment|
      segment.chars.each_with_index do |char, i|
        result[i] << char
      end
    end
    result.join
  end

  def normalize_ciphertext
    ciphertext.scan(/\w{1,5}/).join(' ')
  end

end
