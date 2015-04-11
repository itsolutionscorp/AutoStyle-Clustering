require 'prime' 

class Raindrops 
  def self.convert(number) 
    factors = Prime.prime_division(number) 
    if factors.empty? then 
      ( output = number )
    else 
      output = []
      (0..(factors.size - 1)).each do |index|
        ( output << 'Pling' ) if factors[index].include? 3 
        ( output << 'Plang' ) if factors[index].include? 5 
        ( output << 'Plong' ) if factors[index].include? 7 
      end
      ( output = number ) if output.empty?
    end 
    ( output.class == Array ) ? output.join : output.to_s
  end 
end
