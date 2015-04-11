require 'prime'

class Raindrops
  def self.convert(n)
    return n.to_s if n == 0 || n == 1

    prime = Prime.prime_division(n).flatten
    result = prime.map {|i|
      if i == 3
        'Pling'
      elsif i == 5
        'Plang'
      elsif i == 7
        'Plong'
      end
    }.uniq.join

    (result != '') ? result : n.to_s
  end
end
