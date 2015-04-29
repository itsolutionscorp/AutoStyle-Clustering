class Crypto
  def initialize(text)
    @text = text
  end

  def normalize_plaintext
    @text.gsub(/\W/, '')
      .downcase
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    text = plaintext_segments.join("\n")

    size.times.map do |index|
      text.scan(/^\w{#{index}}(\w)/).flatten.join
    end.join
  end

  def normalize_ciphertext
    ciphertext.gsub(/(\w{#{plaintext_segments.length}})(?!$)/, "\\1 ")
  end

  def size
    text = normalize_plaintext
    Math.sqrt(text.length).ceil
  end
end
