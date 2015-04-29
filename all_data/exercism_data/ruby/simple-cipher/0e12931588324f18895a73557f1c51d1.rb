class Cipher 
  ALPHABET_START = ?a
  ALPHABET_END = ?z
  ALPHABET = (ALPHABET_START..ALPHABET_END).to_a
  RANDOM_KEY_LENGTH = 100
  
  def initialize(key=nil)
    @key = key || generate_random_key
    raise ArgumentError.new('Invalid key! Must be /[a-z]+/!') unless @key =~ /^[a-z]+$/
  end
  
  attr_accessor :key
  
  def encode(plaintext)
    plaintext.chars.map.with_index{ |c, idx| encode_char(c, idx) }.join
  end
  
  def decode(cipher)
    cipher.chars.map.with_index{ |c, idx| decode_char(c, idx) }.join
  end
  
  private
  
  def encode_char(char, index)
    CipherChar.new(char).shift(shift_amount(index))
  end
  
  def decode_char(char, index)
    CipherChar.new(char).shift(-shift_amount(index))
  end
  
  def shift_amount(index)
    @key[index % @key.size].ord - ALPHABET_START.ord
  end
  
  def generate_random_key
    RANDOM_KEY_LENGTH.times.map{ |_| ALPHABET.sample }.join
  end

  class CipherChar
    def initialize(char)
      @value = char.ord - ALPHABET_START.ord
    end
    
    def shift(amount)
      ((@value + amount) % ALPHABET.size + ALPHABET_START.ord).chr
    end
  end
end
