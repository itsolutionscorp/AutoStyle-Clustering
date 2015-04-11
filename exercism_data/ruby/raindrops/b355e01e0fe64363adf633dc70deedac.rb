require 'prime'
class Raindrops
  def self.convert(arg)
    x = arg.prime_division.flatten

    if x.include?(3) && x.include?(7) && x.include?(5)
  	  return "PlingPlangPlong"
    elsif x.include?(3) && x.include?(5)
      return "PlingPlang"
    elsif x.include?(3) && x.include?(7)
      return "PlingPlong"
    elsif x.include?(5) && x.include?(7)
      return "PlangPlong"
     elsif x.include?(3)
      return "Pling"
    elsif x.include?(5)
      return "Plang"
    elsif x.include?(7)
      return "Plong"
    else return arg.to_s
    end
  end
end
