require_relative '../prime-factors/prime_factors.rb'

class Raindrops
  def convert(n)
    f = PrimeFactors.for(n)
    o = [[3,'Pling'],[5,'Plang'],[7,'Plong']].inject('') do |s,i|
      (f.include? i[0]) ? s+i[1] : s
    end
    o.empty? ? n.to_s : o
  end
end
