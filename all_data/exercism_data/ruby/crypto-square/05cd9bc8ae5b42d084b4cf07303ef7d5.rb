# Encrypt a message using a 'square' code
class Crypto
  def initialize(message)
    @message = message
    raise ArgumentError, 'Message is empty' if normalize_plaintext.empty?
  end

  def normalize_plaintext
    @plaintext ||= @message.downcase.gsub(/[^a-z0-9]/,'')
  end
  
  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    plaintext_segments
      .map(&:chars)
      .reduce{ |zipped, seg| zipped.zip(seg) }
      .flatten.compact.join('')
  end

  def normalize_ciphertext
    ciphertext.gsub(/.{5}/,'\& ').strip
  end
end
