class Crypto
  def initialize(message)
    @normalize_plaintext = message.downcase.gsub(/[^a-zA-Z0-9]/, "")
  end

  def normalize_plaintext
    @normalize_plaintext
  end

  def size
    Math.sqrt(@normalize_plaintext.size).ceil
  end

  def plaintext_segments(pad_char: nil)
    text = @normalize_plaintext

    if pad_char
      fill_size = (size ** 2) - text.size
      text << (pad_char * fill_size) if fill_size > 0
    end

    text.chars.each_slice(size).map(&:join)
  end

  def ciphertext
    plaintext_segments(pad_char: "!").map(&:chars)
                                     .transpose
                                     .flatten
                                     .join
                                     .gsub("!","")
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(size).map(&:join).join(" ")
  end
end
