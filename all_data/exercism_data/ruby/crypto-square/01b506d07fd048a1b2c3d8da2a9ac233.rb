class Crypto
  attr_accessor :text
  def initialize(text)
    @text = text
  end

  def normalize_plaintext
    text.downcase.scan(/\w|\d/).join
  end

  def size
    return Math::sqrt(normalize_plaintext.size) if Math::sqrt(normalize_plaintext.size) % 1 == 0
    Math::sqrt(normalize_plaintext.size).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    index = 0
    c = ""
    while index < size
      plaintext_segments.each do |p|
        c << p[index] unless index >= p.size
      end
      index += 1
    end
    c
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,#{plaintext_segments.size}}/).join(' ')
  end
end
