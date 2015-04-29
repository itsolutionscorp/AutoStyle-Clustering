class Crypto
  def initialize(message)
    @message = message
  end

  def size
    @size ||= column
  end

  def ciphertext
    first, *remaining = plaintext_segments
    first.chars.zip(*remaining.map(&:chars)).map(&:join).join
  end

  def normalize_plaintext
    @message.gsub(/[^\w+]/, '').downcase
  end

  def plaintext_segments
    @normalize_plaintext ||= normalize_plaintext.chars.each_slice(size).map(&:join)
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(row).map(&:join).join(" ")
  end

  private

  def column
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def row
    reminder = normalize_plaintext.length % size
    normalize_plaintext.length / size + (reminder == 0 ? 0 : 1)
  end
end
