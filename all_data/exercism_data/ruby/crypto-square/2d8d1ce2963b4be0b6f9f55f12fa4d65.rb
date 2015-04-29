class Crypto
  def initialize(plaintext)
    @normalized_plaintext = plaintext.downcase.gsub(/\W/, '')
  end

  def normalize_plaintext
    @normalized_plaintext
  end

  def size
    Math.sqrt(@normalized_plaintext.length).ceil
  end

  def plaintext_segments
    chunk(@normalized_plaintext, size)
  end

  def ciphertext
    size.times.with_object("") do |index, result|
      result << plaintext_segments.map { |segment| segment[index] }.join
    end
  end

  def normalize_ciphertext
    chunk(ciphertext, 5).join(' ')
  end

  private

  def chunk(string, length)
    string.chars.to_a.each_slice(length).map(&:join)
  end
end
