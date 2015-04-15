module Atbash
  extend self

  def encode(text)
    text.downcase.each_char.map { |c| cipher[c] }.join.scan(/.{1,5}/).join(" ")
  end

private

  def cipher
    @cipher ||= create_cipher
  end

  def create_cipher
    alphabet = [*"a".."z"]
    cipher = Hash[alphabet.zip(alphabet.reverse)]
    (0..9).each { |number| cipher[number.to_s] = number }
    cipher
  end
end
