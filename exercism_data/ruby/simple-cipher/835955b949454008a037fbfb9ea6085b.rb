class Cipher
  ALPHABET = ('a'..'z').to_a

  def initialize key=create_key
    raise ArgumentError unless key =~ /\A[a-z]+\z/
    @key = key
  end

  attr_reader :key

  def endless_key
    return to_enum(:endless_key) unless block_given?

    i = 0
    while true
      yield @key[i % @key.length]
      i+=1
    end
  end

  def encode message
    message.chars.zip(endless_key).map do |c, key_char|
      c1_i = ALPHABET.index(c1)
      c2_i = ALPHABET.index(c2)

      res_i = c1_i + c2_i

      ALPHABET[res_i % ALPHABET.length]
    end.join
  end

  def decode message
    message.chars.zip(endless_key).map do |c, key_char|
      c1_i = ALPHABET.index(c1)
      c2_i = ALPHABET.index(c2)

      res_i = c1_i - c2_i

      ALPHABET[res_i % ALPHABET.length]
    end.join
  end

  def create_key
    100.times.map { ALPHABET.sample }.join
  end
end
