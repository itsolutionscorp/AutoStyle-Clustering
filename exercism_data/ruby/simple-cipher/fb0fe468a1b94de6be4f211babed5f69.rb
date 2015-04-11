class Cipher

  attr_reader :key

  A = 'a'.ord

  def initialize key = generate_key(100)
    raise ArgumentError unless key[ /\A[a-z]+\z/ ]
    @key = key
  end

  def encode plaintext
    plaintext.each_char.with_index do |c,i|
      plaintext[i] = ( (@key[i].ord + c.ord - 2*A) % 26 + A ).chr
    end
  end

  def decode ciphertext
    ciphertext.each_char.with_index do |c,i|
      ciphertext[i] = ( (@key[i].ord - c.ord) % 26 + A ).chr
    end
  end

  private

  def generate_key length
    length.times.with_object('') do |n,key|
      key << (rand(26)+97).chr
    end
  end

end
