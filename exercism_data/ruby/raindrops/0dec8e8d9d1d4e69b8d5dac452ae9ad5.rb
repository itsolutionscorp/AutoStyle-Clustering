class Raindrops

  def self.convert(num)
    str = ""

    primes = {3=> "Pling", 5=> "Plang", 7=> "Plong"}

    primes.each do |key,val|
      if num % key == 0
        str << primes[key]
      end
    end

    str = num.to_s if str.empty?

    str
  end

end
