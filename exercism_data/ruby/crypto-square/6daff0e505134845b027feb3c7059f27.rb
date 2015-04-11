class Crypto
  attr_accessor :text
  def initialize(text)
    @text = text
  end

  def normalize_plaintext
    text.downcase.scan(/\w|\d/).join
  end

  def size
    Math::sqrt(normalize_plaintext.size).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    (0...size).each_with_object("") do |i, c|
      plaintext_segments.each { |p| c << p[i].to_s unless i > p.size }
    end
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,5}/).join(' ')
    # ciphertext.scan(/.{1,#{plaintext_segments.size}}/).join(' ')
  end
end
