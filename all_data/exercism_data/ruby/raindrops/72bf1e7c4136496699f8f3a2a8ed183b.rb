class Raindrops

  def self.convert(number)
    result = nil
    if prime_factor(number, 3)
      result = result_nil?(result, 'Pling')
    end
    
    if prime_factor(number, 5)
      result = result_nil?(result, 'Plang')
    end
    
    if prime_factor(number,7)
      result = result_nil?(result, 'Plong')
    end

    if result.nil? 
      result = number.to_s
    else
      result
    end
  end

  def self.prime_factor(number, factor)
    (number % factor) == 0
  end

  def self.result_nil?(result, drop)
    if result.nil?
      result = drop
    else
      result << drop
    end
  end

end
