class Raindrops
  class << self
    def convert(n)
      nums = [3,5,7] & Converter.new(n).factor
      if nums.any?
        nums.map do |y|
          if y == 3
            'Pling'
          elsif y == 5
            'Plang'
          elsif y == 7
            'Plong'
          end
        end.join('')
      else
        n.to_s
      end
    end
  end
end


class Converter
  attr_accessor :n

  def initialize(n)
    @n = n
    @factors = []
  end

  def factor
    (2..n).each do |x|
      if is_prime?(x) && (n % x == 0)
        @factors << x
        @n = (@n / x)
        break
      end
    end
    return @factors if @n == 1
    factor
  end

  def is_prime?(num)
    (3..num/2).each do |x|
      return false if num % x == 0
    end
    true
  end

end
