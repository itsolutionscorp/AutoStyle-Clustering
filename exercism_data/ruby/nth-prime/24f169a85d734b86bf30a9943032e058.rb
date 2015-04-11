class Prime
  require 'prime'
  def self.nth(input)
    if input > 0
      Prime.take(input).last 
    else
      puts "Try a different numero."
      raise ArgumentError
    end
  end
end

# Ruby 2.1:  Prime::EratosthenesSieve.instance.get_nth_prime(input)
