class Cipher

  def initialize(key)
    @key = key
    raise ArgumentError unless self.key[/[a-z]/]
  end

  def key
    @key ||= generate_random_key
  end

  def encode(plaintext)
    character_values(plaintext).each_with_index.map do |char, index|
      (((key_character_values[index % key.length] + char) % 26 ) + 97).chr
    end.join
  end

  def decode(ciphertext)
    character_values(ciphertext).each_with_index.map do |char, index|
      (((char - key_character_values[index % key.length]) % 26 ) + 97).chr
    end.join
  end

  private
  def generate_random_key
    (0...100).map { (97 + rand(26)).chr }.join
  end

  private
  def character_values(string)
    string.chars.map { |char| char.ord - 97 }
  end

  private
  def key_character_values
    @key_character_values ||= character_values(key)
  end

end
