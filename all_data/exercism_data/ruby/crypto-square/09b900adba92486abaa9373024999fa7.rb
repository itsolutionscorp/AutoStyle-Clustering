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
    plaintext_segments.map{|x| unify(x)}.transpose.join
  end


  def normalize_ciphertext
    ciphertext.scan(/.{1,5}/).join(" ")
  end

  private

  def unify(string)
    result = string.chars
    result.concat(Array.new(size() - string.size)) unless size() == string.size
    result
  end
end
