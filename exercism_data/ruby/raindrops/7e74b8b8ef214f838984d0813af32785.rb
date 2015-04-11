class Raindrops

  def self.convert(num)
    str = ""

    primes = {3=> "Pling", 5=> "Plang", 7=> "Plong"}

    primes.each do |key,val|
      if num % key == 0
        str << primes[key]
      end
    end

    if str.empty?
      str = num.to_s
    end

    str
  end

end
