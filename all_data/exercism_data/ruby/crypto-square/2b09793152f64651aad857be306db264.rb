class Crypto
  def initialize text
    @text = text
  end

  def normalize_plaintext
    @text_normalize ||= text_chars.join
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    text_chars.each_slice(size).map &:join
  end

  def ciphertext
    normalize_ciphertext.delete ' '
  end
  
  def normalize_ciphertext
    dup = segment_chars
    dup.shift.zip(*dup).map(&:join).join ' '
  end

  private
  
  def text_chars
    @text.downcase.scan(/\w/)
  end
  
  def segment_chars
    text_chars.each_slice(size).to_a
  end
end
