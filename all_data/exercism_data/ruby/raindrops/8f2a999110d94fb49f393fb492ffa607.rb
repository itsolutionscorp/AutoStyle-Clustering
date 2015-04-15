require 'prime'
class Raindrops
  def self.convert(number)
    pling, plang, plong = false
    prime_array = Prime.prime_division(number)
    prime_array.each do |array|
      prime_number = array.first
      pling = true if prime_number == 3
      plang = true if prime_number == 5
      plong = true if prime_number == 7
    end

    str = ''
    str << 'Pling' if pling
    str << 'Plang' if plang
    str << 'Plong' if plong
    str.empty? ? number.to_s : str
  end
end
