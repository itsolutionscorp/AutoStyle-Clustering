class Crypto
  def initialize(plaintext)
    @plaintext = plaintext
  end

  def normalize_plaintext
    @plaintext.downcase.scan(/\w/).join
  end

  def size
    closest_integer_square_root(normalize_plaintext.length)
  end

  def plaintext_segments
    normalize_plaintext.chars.each_slice(size).map &:join
  end

  def ciphertext
    (0...size).each_with_object('') do |index, agg|
      agg << plaintext_segments.map{ |s| s[index] }.join
    end
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(5).map(&:join).join(" ")
  end

  private

  def closest_integer_square_root(n)
    (n ** 0.5).ceil
  end
end


