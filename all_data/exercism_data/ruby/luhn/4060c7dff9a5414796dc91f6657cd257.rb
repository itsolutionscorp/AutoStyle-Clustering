class Luhn

  class << self
    def create(a_number)
      l = Luhn.new(a_number*10)
      if l.checksum % 10 > 0
        last_digit = 10 - l.checksum % 10
      else
        last_digit = 0
      end    
      (a_number.to_s+last_digit.to_s).to_i
    end
  end

  def initialize(orig_number)
    @orig_number = orig_number
  end

  def addends
    ret_arr = []
    @orig_number.to_s.reverse.split("").each_with_index do |n,i|
      if i.odd?
        ret_arr << compute(n.to_i)
      else
        ret_arr << n.to_i
      end
    end
    ret_arr.reverse
  end

  def checksum
    addends.reduce(:+)
  end

  def compute(n)
    twice = n*2
    return twice if twice < 10
    twice - 9
  end
  
  def valid?
    checksum % 10 == 0
  end  
end
