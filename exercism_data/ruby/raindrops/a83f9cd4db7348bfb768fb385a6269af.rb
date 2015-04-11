require 'prime'
class Raindrops
  def self.convert(num)
    res = num.prime_division.first(3).map {|p,_v| plingify(p)}.join ''
    return res unless res.empty?
    num.to_s
  end

  def self.plingify(num)
    case num
    when 3 then 'Pling'
    when 5 then 'Plang'
    when 7 then 'Plong'
    end
  end
end
