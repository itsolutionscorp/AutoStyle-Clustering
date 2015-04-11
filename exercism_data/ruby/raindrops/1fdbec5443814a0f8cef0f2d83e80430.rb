require 'prime'

class Raindrops

  def self.convert(x)
    n = x
    result = ''
    primes = Prime.take_while{ |p| p < x }
    raindrops = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}


    while x > 1 do
      primes.reverse.each do |prime|
        if x.modulo(prime).zero?
          x = x / prime
          if raindrops.has_key?(prime)
            result = result + raindrops.delete(prime)
          end
        end
      end
    end
    result.empty? ? n.to_s : result
  end

end
