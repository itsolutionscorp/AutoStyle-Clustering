require 'prime'

class Raindrops
  def self.convert(num)
    str = convert_number_to_string(num)
    str.empty? ? num.to_s : str
  end

  def self.get_prime_numbers(num)
    Prime.prime_division(num).flatten.uniq
  end

  def self.convert_number_to_string(num)
    get_prime_numbers(num).each_with_object('') do |prime, str|
      str << 'Pling' if prime == 3
      str << 'Plang' if prime == 5
      str << 'Plong' if prime == 7
    end
  end
end
