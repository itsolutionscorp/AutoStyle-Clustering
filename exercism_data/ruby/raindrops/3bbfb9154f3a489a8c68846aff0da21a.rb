class Raindrops
  def self.convert(number)
    case gcd(number, 105)
    when 105 then "PlingPlangPlong"
    when 35 then "PlangPlong"
    when 21 then "PlingPlong"
    when 15 then "PlingPlang"
    when 7 then "Plong"
    when 5 then "Plang"
    when 3 then "Pling"
    else
      number.to_s
    end
  end

  def self.gcd(a,b)
    return a if b == 0
    gcd(b, a % b)
  end
end
