class Raindrops
  def self.find_primes(number)
    result = [number]
    result.push(3) if number % 3 == 0
    result.push(5) if number % 5 == 0
    result.push(7) if number % 7 == 0
    result.uniq
  end

  def self.convert(number)
    result = ""
    self.find_primes(number).each do |prime|
      case prime
      when 3
        result += "Pling"
      when 5
        result += "Plang"
      when 7
        result += "Plong"
      end
    end
    result = number.to_s if result == ""
    result
  end
end
