class Sieve
  def initialize number
    @number = number
    @number_list = [*2..number]
  end

  def primes
    primes = []
    until @number_list.empty?
      primes << @number_list.first
      @number_list -= multiple_numbers(@number_list.first)
    end
    primes
  end

  private

  def multiple_numbers number
    (number..@number).select { |n| n % number == 0 }
  end
end
