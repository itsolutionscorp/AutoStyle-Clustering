class SumOfMultiples
  
  def self.new (x1, x2, x3 = nil)   
    @x1,@x2,@x3 = x1,x2,x3
    @reset = true
    self
  end

  def self.to(limit)
    divisors, result = [], []
    unless @reset == true then
      @x1 = 3
      @x2 = 5
      @x3 = nil
      args_count = 2
    else
      @x3 ? args_count = 3 : args_count = 2
    end
       
    for i in 1..args_count 
      divisors << "@x" + i.to_s
    end

      divisors.each{|divisor| 
         divisor = instance_variable_get("#{divisor}") 
         looper = limit/divisor
            for x in 1..looper
              result << x * divisor
              result -= [limit]
            end
      }
      if @reset == true
      @x1 = 3
      @x2 = 5
      @x3 = nil
      args_count = 2
      @reset = false
      end
      
      result.inject(0,:+) 
    end
end
