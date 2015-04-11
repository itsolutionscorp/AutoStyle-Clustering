class Raindrops
  def self.convert(drop)
    primes = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
    ret_val = ''
    
    # check which prime numbers drop is divisible by
    primes.each do |k, v| 
      ret_val += primes[k] if drop % k == 0
    end
    
    # return drop if ret_val is blank, else return our string
    ret_val != '' ? ret_val : ret_val = drop.to_s
  end
end
