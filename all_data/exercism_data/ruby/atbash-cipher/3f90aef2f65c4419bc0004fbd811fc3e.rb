require "pry"

module Atbash
  extend self

  def encode(text)
    cipher = create_cipher
    encryption = ""
    letters(text).each do |letter|
      encryption += cipher[letter]
    end
    groups_of_five(encryption)
  end

  private

  def create_cipher
    alphabet = ("a".."z").to_a
    reversed_alphabet = alphabet.reverse
    cipher = Hash.new { |hash, key| hash[key] = key }
    (0...alphabet.size).each do |index|
      cipher[ alphabet[index] ] = reversed_alphabet[index]
    end
    cipher
  end

  def letters(text)
    text.scan(/\w+/).join.downcase.chars
  end

  def groups_of_five(text)
    text.scan(/.{1,5}/).join(" ")
  end
end
