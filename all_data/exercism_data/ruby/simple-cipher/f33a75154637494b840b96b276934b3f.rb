module ArrayExtensions
  refine Array do
    def infizip other
      self.map.with_index do |element, index|
        [element, other[index % other.size]]
      end
    end
  end
end

class Cipher
  using ArrayExtensions
  attr_reader :key

    C2D = Hash[('a'..'z').zip(0..25)]
    D2C = ('a'..'z').to_a

  def initialize(key = keygen(256))
    @key = key
  end

  def keygen(size)
    size.times.map { D2C.sample }.join
  end

  def enc(chr, key)
    D2C[(C2D[chr] + C2D[key]) % 26]
  end

  def dec(chr, key)
    D2C[(C2D[chr] - C2D[key]) % 26]
  end

  def encode(plaintext)
    plaintext.each_char.to_a.infizip(@key.each_char.to_a).map { |(s, c)| enc(s, c) }.join
  end

  def decode(ciphertext)
    ciphertext.each_char.to_a.infizip(@key.each_char.to_a).map { |(s, c)| dec(s, c) }.join
  end

end
