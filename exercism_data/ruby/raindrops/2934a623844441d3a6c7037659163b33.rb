class Raindrops
  private
  def self.factorize(num)
    factors, divisor = [], 2
    until num == 1
      if num % divisor == 0
        num /= divisor
        factors << divisor
        divisor = 1
      end
      divisor += 1
    end
    factors
  end

  @@chr = {3 => 'i', 5 => 'a', 7 => 'o'}

  def self.convert(number)
    factors = self.factorize(number)
    if not (plngs = factors.grep(3..7).uniq).empty?
      plngs.map{|x| "Pl#{@@chr[x]}ng" }.join
    else
      number.to_s
    end
  end
end
