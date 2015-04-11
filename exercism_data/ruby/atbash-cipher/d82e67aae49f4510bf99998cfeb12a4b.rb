class Atbash
  def self.encode(message)
    message
      .downcase
      .scan(/[[:alnum:]]/)
      .map(&method(:crypt))
      .join
      .scan(/.{1,5}/)
      .join(' ')
  end

  private
  ALPHABET = 'abcdefghijklmnopqrstuvwxyz'.chars
  REVERSE = ALPHABET.reverse

  def self.crypt(char)
    id = ALPHABET.index(char)
    id and REVERSE[id] or char
  end
end
