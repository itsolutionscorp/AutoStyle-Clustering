class Cipher
  
  def initialize(key=nil)
    @key = key
    raise ArgumentError unless self.key[/[a-z]/]
  end

  def key
    @key ||= generate_random_key
  end

  def encode(plaintext)
    plaintext.chars.each_with_index.map do |char, index|
      char_code = "a".ord + shift_distance(char, index)
      char_code.chr
    end.join
  end

  def decode(ciphertext)
    ciphertext.chars.each_with_index.map do |char, index|
      char_code = "a".ord + reverse_shift_distance(char, index)
      char_code = rotate_reverse(char_code) if char_code < "a".ord
      char_code.chr
    end.join
  end

  private
  def shift_distance(char, index)
    distance = (char.ord - "a".ord) + (@key[index].ord - "a".ord)
    distance = distance % 26 if distance > 25 # rotate back between a and z
    distance
  end  
  
  def reverse_shift_distance(char, index)
   (char.ord - "a".ord) - (@key[index].ord - "a".ord)
  end 

  def rotate_reverse(char_code)
    "z".ord - ("a".ord - 1 - char_code) 
  end  
    
  def generate_random_key
    (0...100).map { ("a".ord + rand(26)).chr }.join
  end
end
