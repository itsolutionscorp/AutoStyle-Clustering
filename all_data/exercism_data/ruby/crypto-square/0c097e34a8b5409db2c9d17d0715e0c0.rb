class Crypto
 
  def initialize(text)
    @text = text.downcase.gsub(/\W/,"")
  end

  def normalize_plaintext
    @text
  end

  def size
    Math::sqrt(@text.length).ceil
  end

  def plaintext_segments
    @text.scan(/.{1,#{size()}}/)
  end

  def ciphertext
    plaintext_segments.map{|x| fit_to_size(x)}.transpose.join.gsub(/\W/,'')
  end


  def normalize_ciphertext
    ciphertext.scan(/.{1,5}/).join(" ")
  end

  private

  def fit_to_size(string)
    string.ljust(size()).chars
  end
end
