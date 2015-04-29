class Crypto
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def normalize_plaintext
    text.gsub(/\W+/, "").downcase
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
     normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    "".tap do |cypher|
      (0..size).each do |i|
        plaintext_segments.each { |s| cypher << s[i] if s[i] }
      end
    end
  end

  def normalize_ciphertext
    size = plaintext_segments.size
    ciphertext.scan(/.{1,#{size}}/).join(" ")
  end
end
