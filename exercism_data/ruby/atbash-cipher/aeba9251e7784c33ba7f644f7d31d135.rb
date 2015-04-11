class Atbash
  def self.encode(text)
    new(text).encode
  end

  def initialize(text)
    @text = text
    @cipher = build_cipher
  end

  def encode
    encoded_text = letters.map { |letter| @cipher[letter] }.join
    groups_of_five(encoded_text)
  end

  private

  def letters
    @text.scan(/\w/)
  end

  def groups_of_five(text)
    text.scan(/.{1,5}/).join(" ")
  end

  def build_cipher
    alphabet = ("a".."z").to_a
    Hash.new do |hash, key|
      if alphabet.include?(key.downcase)
        index = alphabet.index(key.downcase)
        hash[key] = alphabet[-index - 1]
      else
        hash[key] = key
      end
    end    
  end
end
