class Raindrops

  def self.convert(number)
    return_string = '' #Start with empty string
    if number % 3 == 0
      return_string << "Pling" #If 3 is prime factor, shovel Pling
    end
    if number % 5 == 0
      return_string << "Plang" #If 5 is prime factor, shovel Plang
    end
    if number % 7 == 0
      return_string << "Plong" #If 7 is prime factor, shovel Plong
    end
    if return_string == '' #If none of the above, pass as string
      return_string = number.to_s
    end
    return_string #Return final answer
  end
end
