class Raindrops
  @words = { 3 => "Pling" , 5 => "Plang", 7 => "Plong" }

  def self.prime_factors(n)
    return [] if n == 1
    factor = (2..n).find {|x| n % x == 0}
    [factor] + prime_factors(n / factor)
  end

  def self.convert(number)
    speak = ''
    prime_factors(number).uniq.each do |i|
      if @words[i]
        speak << @words[i]
      end
    end
    if speak.empty?
      return number.to_s
    else
      return speak
    end
  end
end
