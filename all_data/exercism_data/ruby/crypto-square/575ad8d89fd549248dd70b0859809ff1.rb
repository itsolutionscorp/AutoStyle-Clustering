class Crypto
  attr_reader :message

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
    coll = []
    plaintext_segments.first.length.times do |i|
      coll << plaintext_segments.map{|s| s[i]}
    end
    coll.join
  end

  def normalize_ciphertext
    segment(ciphertext, 5).join(" ")
  end

  private

  def segment(text, length)
    text.chars.each_slice(length).map{|slice| slice.join}.to_a
  end
end
