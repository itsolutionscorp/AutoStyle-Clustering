require 'securerandom'

class Robot
  attr_accessor :name
  
  def randname
    letters = (65+rand(26)).chr + (65+rand(26)).chr
    num = '000'+(SecureRandom.random_number(999)).to_i.to_s
    threeDigit = num[num.length-3..-1]
    letters+threeDigit
  end

  def initialize
    @name = randname
  end

  def reset
    oldname = @name
    unless oldname!=@name
      @name = randname
    end
  end
end
