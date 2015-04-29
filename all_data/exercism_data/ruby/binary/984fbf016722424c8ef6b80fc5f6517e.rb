#!/usr/bin/ruby -w

class Binary
  ZERO = "0".ord

  attr_accessor :s

  def initialize s
    self.s = s
  end

  def to_decimal
    sum = 0

    s.bytes.reverse_each.with_index do |byte, base|
      digit = byte - ZERO

      return 0 unless digit.between?(0, 9)

      sum += digit * 2**base
    end

    sum
  end
end
