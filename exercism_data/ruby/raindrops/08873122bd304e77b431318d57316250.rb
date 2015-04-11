class Raindrops
  require 'prime'

  def self.convert(number)
    mapping = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }

    prime_factors = Prime.prime_division(number)
    prime_factors = prime_factors.map { |x| mapping[x[0]] }
                                 .reject { |x| x.nil? }

    if prime_factors.empty?
      number.to_s
    else
      prime_factors.join("")
    end
  end
end
