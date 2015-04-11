class Raindrops
  def self.convert(number)
    result = ''
    if number % 3 == 0 then result += 'Pling' end
    if number % 5 == 0 then result += 'Plang' end
    if number % 7 == 0 then result += 'Plong' end

    if result == ''
      return number.to_s
    else
      return result
    end
  end
end

#- If the number contains 3 as a prime factor, output 'Pling'.
#- If the number contains 5 as a prime factor, output 'Plang'.
#- If the number contains 7 as a prime factor, output 'Plong'.
#- If the number does not contain 3, 5, or 7 as a prime factor,
  #just pass the number's digits straight through.
