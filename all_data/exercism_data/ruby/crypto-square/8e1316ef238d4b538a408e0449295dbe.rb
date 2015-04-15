class Crypto

  def initialize(text)
    @text = text
  end

  def normalize_plaintext
    @text.scan(/\w+/).map(&:downcase).join
  end

  def size
    Math.sqrt(normalize_plaintext.size).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/\w{1,#{size}}/)
  end

  def ciphertext
    result = []
    size.times { |x| result << '' }
    plaintext_segments.each do |segment|
      segment.split('').each_with_index do |char, i|
        result[i] << char
      end
    end
    result.join
  end

  def normalize_ciphertext
    ciphertext.scan(/\w{1,#{size}}/).join(' ')
  end

end
