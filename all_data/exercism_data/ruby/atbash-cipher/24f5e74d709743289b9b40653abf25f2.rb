class Atbash
  class << self
    def encode(text)
      cipher = AtbashCipher.new
      encoded_text = text.scan(/\w/).inject("") do |encoded_text, letter|
        encoded_text += cipher.encode(letter)
      end
      encoded_text.scan(/.{1,5}/).join(" ")
    end
  end
end

class AtbashCipher
  def initialize
    @mappings = Hash.new { |hash, key| hash[key] = key }
    populate_mappings 
  end

  def encode(character)
    @mappings[character]
  end

  private

  def populate_mappings
    alphabet = ("a".."z").to_a
    alphabet.each_with_index do |letter, index|
      @mappings[letter] = @mappings[letter.upcase] = alphabet[-index - 1]
    end
  end
end
