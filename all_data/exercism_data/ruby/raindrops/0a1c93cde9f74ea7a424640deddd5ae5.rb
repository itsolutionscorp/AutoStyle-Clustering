class Raindrops
  require 'prime'

  def self.convert(num)
    values = Prime.prime_division(num)
    result = ''
    values.each do |x,y|
      case x
      when 3 then result += 'Pling' if !result.include?('Pling')
      when 5 then result += 'Plang' if !result.include?('Plang')
      when 7 then result += 'Plong' if !result.include?('Plong')
      else end
    end
    result.empty? ? num.to_s : result
  end
end
