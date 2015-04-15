class PrimeFactors
  def self.for(num)
    @number = num
    prime_array = []
    while @number > 1
      (2..@number).each do |x|
        if @number % x == 0
          @number = @number/x
          prime_array << x
          break
        end
      end
    end
    prime_array
  end
end
