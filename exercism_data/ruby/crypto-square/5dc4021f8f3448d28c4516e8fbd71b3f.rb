class Crypto
  def initialize(message)
    @message = message
  end

  def normalize_plaintext
    @normalized ||= message.gsub(/\W+/, "").downcase
  end

  def size
    @size ||= Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    segment(normalize_plaintext, size)
  end

  def ciphertext
    size.times.map do |i|
      plaintext_segments.map { |s| s[i] }
    end.join
  end

  def normalize_ciphertext
    segment(ciphertext, 5).join(" ")
  end

  private

  attr_reader :message

  def segment(text, length)
    text.chars.each_slice(length).map(&:join).to_a
  end
end
