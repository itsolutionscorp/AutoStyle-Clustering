class Crypto
  attr_reader :characters, :size

  alias :normalize_plaintext :characters

  def initialize(characters)
    @characters = characters.downcase.gsub(/\W+/, "")
  end

  def size
    @size ||= Math.sqrt(characters.size).ceil
  end

  def plaintext_segments
    segments(normalize_plaintext, size)
  end

  def ciphertext
    @ciphertext ||= plaintext_segments.each_with_object(Array.new(size) { String.new }) do |word, result|
      word.each_char.with_index { |char, index| result[index] << char }
    end.join
  end

  def normalize_ciphertext
    segments(ciphertext, Math.sqrt(ciphertext.size).round).join(" ")
  end

  private

    def segments(string, length)
      string.scan(/.{1,#{length}}/)
    end
end
