require 'prime'

class Raindrops

  class << self

    def convert number
      sounds = {3=>'Pling', 5=>'Plang', 7=>'Plong'}
      factors = number.prime_division.flatten.reject { |n| n == 1 }
      
      responses = sounds.inject([]) do |results, (key, val)|
        results << val if factors.include? key 
        results
      end

      speak = if responses.empty?
        number.to_s
      else
        responses.join  
      end
      
      speak
    end

  end

end
