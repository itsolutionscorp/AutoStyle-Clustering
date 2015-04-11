class Raindrops
  require 'prime'

  def self.convert(num)
    values = Prime.prime_division(num)
    result = ''
    values.each do |prime,_|
      case prime
      when 3 then result << 'Pling'
      when 5 then result << 'Plang'
      when 7 then result << 'Plong'
      else end
    end
    result.empty? ? num.to_s : result
  end
end
