#
# Raindrop Class
#
require 'prime'

class Raindrops

  def self.convert(num)
    translate = ""
    Prime.prime_division(num).each do |x,y|
      if [3,5,7].include? x
       translate.concat(x.to_s)
      end
    end
    result = translate.gsub!(/[357]/, '3' => 'Pling', '5' => 'Plang', '7' => 'Plong')
    result.nil? ? num.to_s : result 
  end
end
