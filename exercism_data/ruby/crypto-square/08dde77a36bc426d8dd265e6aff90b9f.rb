class Crypto
  attr_reader :size, :normalize_plaintext

  def initialize text
    @text = text
    @normalize_plaintext = text.downcase.chars.select { |l| l =~ /[[:alpha:][:digit:]]/ }.join
    @size = Math.sqrt(normalize_plaintext.length).ceil
  end


  def plaintext_segments
    normalize_plaintext.chars.each_slice(size).map { |a| a.join }
  end


  def ciphertext
    len = normalize_plaintext.length
    rv = []
    i = 0
    while rv.length <= len
      rv << normalize_plaintext[i]
      i += size
      i = i%size+1 if i > len
    end
    rv.join
  end


  def normalize_ciphertext
    ciphertext.chars.each_slice(5).map { |a| a.join }.join ' '
  end

end
