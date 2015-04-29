class Raindrops
  class << self

    REL_PRIMES = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

    def convert (number)
      num_str = ""
      REL_PRIMES.each do |k, v|
        if number%k == 0
          num_str = num_str + v
        end
      end

      if num_str.length == 0
        num_str = number.to_s
      end

      return num_str

    end

  end
end
