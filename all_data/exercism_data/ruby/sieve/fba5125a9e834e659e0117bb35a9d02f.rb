class Sieve
  def initialize(number)
    @number = number
  end
  def primes
    numbers = (2..@number).to_a
    primes = (2..@number).to_a
    numbers.each do |num|
      break unless primes.include?(num)
      primes.each do |prime|
        puts "num: #{num}, prime: #{prime}"
        if prime != num && prime % num == 0
          puts "deleting prime #{prime} now!"
          primes.delete(prime)
        end
      end
    end
    primes
  end
end
