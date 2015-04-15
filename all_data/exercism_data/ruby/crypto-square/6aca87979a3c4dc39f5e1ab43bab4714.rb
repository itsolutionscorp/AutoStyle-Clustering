class Crypto
  def initialize(text)
    @text = text
  end

  def normalize_plaintext
    @text.downcase.gsub(/[^a-z1-9]/,"")
  end

  def size
    Math.sqrt(@text.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(Regexp.new(".{1,#{size - shift}}"))
  end

  def shift
    perfect_square? ? 0 : 1
  end

  def perfect_square?
    Math.sqrt(@text.length).to_i == Math.sqrt(@text.length)
  end

  def ciphertext
    size.times.flat_map { |index| plaintext_segments.map { |segment| segment[index] } }.join
  end

  def normalize_ciphertext
    ciphertext.scan(Regexp.new(".{1,#{size - shift - 1}}")).join " "
  end
end
