class Crypto
  attr_reader :normalize_plaintext
  def initialize(string)
    @normalize_plaintext = string.gsub(/[^a-zA-Z\d]/, '').downcase
  end

  def size
    Math.sqrt(@normalize_plaintext.length).ceil
  end

  def plaintext_segments
    result = []
    @normalize_plaintext.split(//).each_slice(size) { |x| result << x.join }
    result
  end

  def ciphertext
    result = ''
    i = 0
    while i < size
      plaintext_segments.each do |x|
        result << x[i] unless x[i].nil?
      end
      i += 1
    end
    result
  end

  def normalize_ciphertext
    length = Math.sqrt(ciphertext.length).round
    result = []
    ciphertext.split(//).each_slice(length) { |x| result << x.join }
    result.join(' ')
  end
end
