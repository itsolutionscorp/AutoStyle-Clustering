require 'prime'

class Raindrops

  def self.convert(num)
    numbers_list = []
    string = ''
    prime_fact = Prime.prime_division(num)
    if num == 1
      return num.to_s
    else
      prime_fact.each do |array|
        array.each do |number|
          numbers_list.push(number)
        end
      end
      if numbers_list.any? {|prime| prime == 3}
        string = 'Pling'
      end
      if numbers_list.any? {|prime| prime == 5}
        string = string + 'Plang'
      end
      if numbers_list.any? {|prime| prime == 7}
        string = string + 'Plong'
      end
    end
    if string == ''
      return num.to_s
    else
      return string
    end
  end

end
