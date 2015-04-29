LETTERS = 'abcdefghijklmnopqrstuvwxyz'
    
class Atbash
  def self.encode(s)
    message = Encodable.new(s)
    message.ciphertext
  end
end

class Encodable
  attr_reader :plaintext, :ciphertext

  def initialize(s)
    @plaintext = sanitize(s)
    @ciphertext = segment(encode(plaintext))
  end

  private

  def sanitize(s)
    s.downcase.gsub(/\W/, '')
  end

  def encode(s)
    s.tr(LETTERS, LETTERS.reverse)
  end

  def segment(s)
    s.scan(/.{1,5}/).join(' ')
  end
end
