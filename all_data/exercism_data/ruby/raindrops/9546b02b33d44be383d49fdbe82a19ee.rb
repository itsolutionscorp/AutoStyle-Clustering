class Raindrops
  def self.prime_factors(number)
    [3,5,7].select{|i| number%i == 0}
  end

  def self.factor_to_string(prime_factor)
    case prime_factor
      when 3
        'Pling'
      when 5
        'Plang'
      when 7
        'Plong'
    end
  end

  def self.convert(number)
    prime_factors = Raindrops.prime_factors(number)
    if prime_factors.empty?
      number.to_s
    else
      prime_factors.map do |prime_factor|
        Raindrops.factor_to_string(prime_factor)
      end.join('')
    end
  end
end
