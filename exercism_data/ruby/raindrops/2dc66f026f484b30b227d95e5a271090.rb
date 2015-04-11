class Raindrops
  def self.convert_prime_factors!(prime)
    conversion_routes = {3 => "Pling" , 5 => "Plang", 7 => "Plong"}

    if conversion_routes.keys.include? prime
      unless @result_of_conversion.class.eql? String
        @result_of_conversion = String.new
      end
      converted_value = conversion_routes[prime]
      unless @result_of_conversion.include? converted_value
        @result_of_conversion += converted_value
      end
    end

  end

  def self.convert(input)
    prime = 2
    @result_of_conversion = number = input
    until number.eql? 1 do
      if (number % prime).zero?
        number /= prime
        convert_prime_factors!(prime)
      else
        prime = prime.next
      end
    end
    @result_of_conversion.to_s
  end
end
