require 'prime'

class Raindrops

  attr_accessor :prime_array

  def self.convert(number)
    @prime_array = Array.new
  	self.find_prime_factors(number)
  	return_string = String.new
    return_string += "Pling" if @prime_array.include?(3)
    return_string += "Plang" if @prime_array.include?(5)
    return_string += "Plong" if @prime_array.include?(7)
  	return_string = number.to_s if return_string.empty?
  	return return_string
  end


  private
  def self.find_prime_factors(number)
  	divider = find_biggest_divider(number)
    if divider == 1
      @prime_array.push(number) if divider == 1
      return
    end
    @prime_array.push(number/divider)
    self.find_prime_factors(divider)
  end

  def self.find_biggest_divider(number)
    divider = 1
    number.downto(1) do |i|
      next if i == number 
      if number % i == 0
        divider = i
        break
      end
    end
    divider
  end


end
