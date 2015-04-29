class Raindrops

  def self.prime_factors
    [2,3,5,7]
  end

  def self.convert(number)
    return "PlingPlangPlong" if factor_3?(number) && factor_5?(number) && factor_7?(number)
    return "PlingPlang" if factor_3?(number) && factor_5?(number)
    return "PlingPlong" if factor_3?(number) && factor_7?(number)
    return "PlangPlong" if factor_5?(number) && factor_7?(number)
    return "Pling" if factor_3?(number)
    return 'Plang' if factor_5?(number)
    return 'Plong' if factor_7?(number)
    else number.to_s
  end

  def self.factor_3?(number)
    number == 3 || number %3 == 0
  end

  def self.factor_5?(number)
    number == 5 || number %5 == 0
  end

  def self.factor_7?(number)
    number == 7 || number %7 == 0
  end

end
