class Crypto
  def initialize(message)
    @message = message.scan(/\w+/).join.downcase
  end

  def normalize_plaintext
    return @message
  end

  def size
    Math.sqrt(@message.length).ceil
  end

  def plaintext_segments
    @message.scan(/.{#{size}}|.+/)
  end

  def ciphertext
    create_cipher
  end

  def normalize_ciphertext
    create_cipher(true).strip
  end

  private
  
  def create_cipher(normalize=false, cipher="")

    (0..size).each do |i|
      plaintext_segments.each do |segment|
        cipher += segment[i].to_s
      end
      if normalize == true
        cipher += " "
      end
    end
    return cipher
  end

end
