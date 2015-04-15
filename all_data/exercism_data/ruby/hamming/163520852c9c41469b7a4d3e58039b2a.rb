#!/usr/bin/env ruby

class Hamming
  attr_reader :s1, :s2

  def self.compute(s1, s2)
    @count = 0
    @s1 = s1
    @s2 = s2

    @s1.length < @s2.length ? @shorter = @s1.length : @shorter = @s2.length

    if @s1.length < @s2.length
      @shorter = @s1.length
    else
      @shorter = @s2.length
    end

    0.upto(@shorter-1) do |x|
      if @s1[x] != @s2[x]
        @count = @count + 1
      end
    end

    return @count
  end
end
