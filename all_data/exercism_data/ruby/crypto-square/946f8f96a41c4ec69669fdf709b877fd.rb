class Crypto
  def initialize(text)
    @text = text
  end

  def normalize_plaintext
    @text.gsub(/\W/, '')
      .downcase
  end

  def plaintext_segments
    normalize_plaintext
      .chars
      .each_slice(size)
      .to_a
      .map { |word_array| word_array.join('')}
  end

  def ciphertext
    text = plaintext_segments.join("\n")

    size.times.map do |index|
      text.scan(/^\w{#{index}}(\w)/).flatten.join
    end.join
  end

  def normalize_ciphertext
    how_many = size - 1
    how_many = how_many >= 2 ? how_many : 2

    ciphertext.gsub(/(\w{#{how_many}})(?!$)/, "\\1 ")
  end

  def size
    text = normalize_plaintext
    Math.sqrt(text.length).ceil
  end
end
