# class Crypto
class Crypto
  attr_accessor :str
  def initialize(str)
    @str = str
  end

  def normalize_plaintext
    str.gsub(/[^0-9A-Za-z]/, '').downcase
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.gsub(/(\w{#{size}})/, '\1-').split('-')
  end

  def ciphertext
    ct = []
    plaintext_segments.first.length.times do |i|
      ct << plaintext_segments.map { |e| e[i] }.join
    end
    ct.join
  end

  def normalize_ciphertext
    ciphertext.gsub(/(\w{5})/, '\1 ').strip
  end
end
