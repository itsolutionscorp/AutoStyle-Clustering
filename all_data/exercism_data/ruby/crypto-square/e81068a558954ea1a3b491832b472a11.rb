class Float
  def integer_value?
    self % 1 == 0
  end
end

class Crypto
  def initialize(plaintext)
    @plaintext = plaintext
  end

  def normalize_plaintext
    @plaintext.downcase.scan(/\w/).join
  end

  def size
    closest_integer_square_root normalize_plaintext.length
  end

  def plaintext_segments
    normalize_plaintext.chars.each_slice(size).map &:join
  end

  def ciphertext
    (0...size).each_with_object(''){|index, agg|
      agg << plaintext_segments.map{|s|s[index]}.join
    }    
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(5).map(&:join).join(" ")
  end

  private

  def closest_integer_square_root(n)
    sqrt = n ** 0.5
    Integer(sqrt.integer_value? ? sqrt : sqrt + 1)
  end
end


