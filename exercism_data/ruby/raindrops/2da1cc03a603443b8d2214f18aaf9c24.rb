# raindrops.rb
# author: Ray Wach
# date: 2015-01-08

class Raindrops
  def self.convert(num)
    str = prime_factors(num).collect do |factor|
      case factor
      when 3
        'Pling'
      when 5
        'Plang'
      when 7
        'Plong'
      end
    end.join
    str.empty? ? num.to_s : str
  end

  # Simple factor sieve
  def self.prime_factors(num)
    ret_arr = []
    2.upto(num) do |i|
      if num % i == 0
        ret_arr <<= i
        num /= i
      end
    end
    ret_arr
  end
end
