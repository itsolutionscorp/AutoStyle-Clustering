class Atbash

  ALPHABET = ('a'..'z').to_a

  def self.encode string
    characters = string.downcase.chars.select{|c| c.match(/\w/)}
    characters.map{|c| character_code(c)}.join.scan(/.{1,5}/m).join(' ')
  end

  def self.character_code c
    ALPHABET.include?(c) ? ALPHABET[-(ALPHABET.index(c)+1)] : c
  end

end
