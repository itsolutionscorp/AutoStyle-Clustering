class Atbash
  def self.encode(plaintext)
    washed_text = wash(plaintext)
    cypher_string = atbash_encrypt(washed_text)
    cyphertext = spaces_every_5(cypher_string)
    return cyphertext
  end

  private

  def self.wash(plaintext)
    plaintext.downcase.scan(/[a-z0-9]/).join
  end

  def self.atbash_encrypt(washed_text)
    washed_text.gsub(/[a-z]/) do |plain_char|
      numeric_char = plain_char.unpack('C')[0] - 96
      encrypted_char = (14 - (numeric_char - 13) + 96).chr
    end
  end

  def self.spaces_every_5(cypher_string)
    return cypher_string.scan(/.{5}|.+/).join(" ")
  end
end
