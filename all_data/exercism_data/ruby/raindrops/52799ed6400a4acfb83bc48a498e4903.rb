class Raindrops
  DIVISORS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert n
    divisor_texts = 
      DIVISORS
        .select{|divisor, output| n % divisor == 0 }
        .map{|divisor, output| output }
        .reduce :+

    divisor_texts || n.to_s
  end
end
