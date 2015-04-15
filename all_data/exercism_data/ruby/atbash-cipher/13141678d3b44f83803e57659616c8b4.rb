module Atbash
  ALPHABET = ("a".."z").to_a
  NUMBERS = ("0".."9").to_a

  def self.encode(message)
    encoded = parse(message).map do |l|
      NUMBERS.include?(l) ? l : encode_letter(l)
    end.join

    add_spaces(encoded)
  end

  private

  def self.parse(message)
    message.scan(/(\w|\d)/).flatten
  end

  def self.encode_letter(l)
    i = ALPHABET.index(l.downcase)
    rot = 25 - i
    ALPHABET[rot]
  end

  def self.add_spaces(message)
    message.scan(/.{0,5}/).join(' ').strip
  end

end
