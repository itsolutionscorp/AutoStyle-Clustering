class Raindrops

  def self.primes (num)
    (1..num).select { |x| num % x == 0 }
  end

  def self.convert (num)
    rain_str = primes(num).inject('') do |string, prime|
      case prime
      when 3
        string.concat('Pling')
      when 5
        string.concat('Plang')
      when 7
        string.concat('Plong')
      else
        string
      end
    end
    return (rain_str != '' ? rain_str : num.to_s)
  end

end
