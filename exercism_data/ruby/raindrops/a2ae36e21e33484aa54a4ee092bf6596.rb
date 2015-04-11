class Raindrops
  require 'prime'

  def self.convert(num)
    original_num = num
    @prime_array = self.create_prime_array(10)
    prime_factors = []

    @prime_array.each do |n|
      if num % n == 0
        num = num / n
        prime_factors << n
      end
    end
    
    output = []
    flag = false
    output << 'Pling' && flag = true if prime_factors.include? 3
    output << 'Plang' && flag = true if prime_factors.include? 5
    output << 'Plong' && flag = true if prime_factors.include? 7
    output = output.join if flag
    output = original_num.to_s unless flag
    output
  end

  def self.create_prime_array(amount)
    @prime_array = []
    Prime.each(amount) do |prime|
      @prime_array << prime  
    end
    @prime_array
  end

end
