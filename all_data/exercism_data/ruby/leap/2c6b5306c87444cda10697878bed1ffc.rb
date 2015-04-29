class Year
  def self.leap? year
    case find_even_divisors(year.to_i, [4, 100, 400])
      when [4], [4, 100, 400] then true
      else false
    end
  end

  def self.find_even_divisors year, divisors
    divisors.keep_if {|divisor| (year % divisor) == 0}
  end
end
