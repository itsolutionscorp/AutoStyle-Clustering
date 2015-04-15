class Sieve

  def initialize(max)
    @list = (2..max).to_a
  end
  
  def primes
    @list.each_with_index do |num1, idx|
      @list[idx+1..-1].each do |num2| 
        @list.delete(num2) if num2 % num1 == 0
      end
    end

    @list
  end
end
