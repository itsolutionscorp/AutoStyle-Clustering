class Crypto
  CIPHERTEXT_LINE_LENGTH = 5

  def initialize(encrypted_message)
    @encrypted_message = encrypted_message
  end

  def normalize_plaintext
    @encrypted_message.gsub(/\W/, "").downcase
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments(text = normalize_plaintext(), slice_size = nil)
    slice_size ||= size()
    i = 0
    segments = []
    text_size = text.size
    while i < text_size
      segments << text[i, slice_size]
      i += slice_size
    end
    segments
  end

  def ciphertext
    segments = plaintext_segments
    size = size()
    text = ""
    size.times do |i|
      segments.each do |segment|
        text << segment[i].to_s
      end
    end
    text
  end

  def normalize_ciphertext
    plaintext_segments(ciphertext, CIPHERTEXT_LINE_LENGTH).join(" ")
  end
end
