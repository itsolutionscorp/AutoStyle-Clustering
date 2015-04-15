#!/usr/bin/env ruby

# Exercism 20
# Phone Numbers

# Clean up user input phone numbers so they can receive SMS

class PhoneNumber

  def initialize(num)
    @num = num
  end

  def number
    @num.delete!' ()-.'
    @num = @num.to_i.to_s

    if @num.length == 10
      @num
    elsif @num.length == 11
      @num[0] == '1' ? @num[1..10] : '0'*10
    else
      '0'*10
    end
  end

  def area_code
    @num[0..2]
  end

  def to_s
    if @num.length == 11
      "(#{@num[1..3]}) #{@num[4..6]}-#{@num[7..11]}"
    elsif @num.length == 10
      "(#{@num[0..2]}) #{@num[3..5]}-#{@num[6..10]}"
    else
      '0'*10
    end
  end

end
