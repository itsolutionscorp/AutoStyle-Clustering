require 'pry-byebug'

class PrimeFactors

  def self.for(i) 
    @num = i
    @array_holding_first_factor = []

    2.upto(@num) { 
      |x| if @num % x == 0
      @array_holding_first_factor << x
      break if @array_holding_first_factor.empty? == false
      end
      }

    if @array_holding_first_factor.empty?
        @array_holding_first_factor
      else
      @first = @array_holding_first_factor.shift
      self.factoring_loop(@first)
    end
  end

  def self.factoring_loop(i)
    next_factor = i
    factors = []
    until @num == next_factor do     

      begin
        factors << next_factor
        @num /= next_factor
      end until (@num.to_f / next_factor).is_a?(Float)
      
      until @num % next_factor == 0 do   
        next_factor += 1
      end

    end

    factors << next_factor
    factors
  end
end

PrimeFactors.for(60)
