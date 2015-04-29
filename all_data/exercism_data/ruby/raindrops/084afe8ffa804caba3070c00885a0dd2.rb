require "prime"
class Raindrops
  attr_accessor :primees
  def convert (rain)
    return "1" if rain == 1
    self.primees = []
    primetest rain
    if primees == []
      return rain.to_s
    end
    
    result = primees.each_with_object([]) do |num, final|
      final << (name2 num)
    end
    result.uniq.join

  end

  def prime? value
    Prime.prime?(value)
  end
  def div_by  number, value
    if value % number == 0
      return value/number
    end
  end

  def div_by_and_save number, value
    result = div_by number, value
    if result != nil
      if number != 2
        self.primees << number
      end
    end
    result
  end

  def primetest value
    [2,3,5,7].each do |prime|
      result = div_by_and_save prime, value
      if result != nil
        primetest result
        break
      end
    end
  end

  def name2 value
    return "Pling" if value == 3
    return "Plang" if value == 5
    return "Plong" if value == 7
    return value.to_s
  end
end
