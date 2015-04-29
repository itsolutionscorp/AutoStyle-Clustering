class Atbash

  ALPHABET = ('a'..'z').to_a

  def self.encode string
    characters = normalize string
    codes = characters.map{|code| lookup code}
    format codes
  end

  def self.normalize string
    string.downcase.chars.select{|c| c.match(/\w/)}
  end

  def self.lookup code
    ALPHABET.include?(code) ? ALPHABET[-(ALPHABET.index(code)+1)] : code
  end

  def self.format codes
    codes.join.scan(/.{1,5}/m).join(' ')
  end

end
