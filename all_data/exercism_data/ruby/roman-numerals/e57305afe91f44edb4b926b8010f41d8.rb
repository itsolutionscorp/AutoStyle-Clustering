class Fixnum

  NUMERALS = { 'M' => 1000, 'CM' => 900, 'D' => 500, 'CD' => 400,
               'C' => 100, 'XC' => 90, 'L' => 50, 'XL' => 40,
               'X' => 10, 'IX' => 9, 'V' => 5, 'IV' => 4, 'I' => 1
  }
  def to_roman
    accm = []
    nums = self.to_s.split('')
    places_cntr = 1
    while x = nums.pop
      x = x.to_i
      if x != 0
        if NUMERALS.values.include?(x*places_cntr)
          accm.unshift(NUMERALS.key(x*places_cntr))
        else
          if x > 5
            accm.unshift(NUMERALS.key(places_cntr)*(x-5))
            accm.unshift(NUMERALS.key(5*places_cntr))
          else
            accm.unshift(NUMERALS.key(places_cntr)*x)
          end
        end
      end
      places_cntr *= 10
    end
    accm.join('')
  end
end

# I - 1
# V - 5
# X - 10
# L - 50
# C - 100
# D - 500
# M - 1000
