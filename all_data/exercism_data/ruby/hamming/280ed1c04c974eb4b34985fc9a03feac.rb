require 'contracts'

class Hamming
  include Contracts

  Contract String, String => Num
  def self.compute(a, b)
    if a == b
      return 0
    elsif one_char_remaining?(a, b)
      return (a[0] == b[0] ? 0 : 1)
    else
      return compute(first_half(a), first_half(b)) +
             compute(second_half(a), second_half(b))
    end
  end

  private

  Contract String, String => Bool
  def self.one_char_remaining?(a, b)
    a.length == 1 || b.length == 1
  end

  Contract String => String
  def self.first_half(msg)
    msg.slice(0, (msg.length / 2.0).ceil)
  end

  Contract String => String
  def self.second_half(msg)
    msg.slice((msg.length / 2.0).ceil, msg.length)
  end
end
