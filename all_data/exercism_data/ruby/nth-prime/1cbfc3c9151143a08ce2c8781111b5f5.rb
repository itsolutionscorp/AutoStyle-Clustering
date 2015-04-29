require 'prime'

class Prime
  def self.nth(number)
    raise ArgumentError.new("0 is not valid!") if number == 0
    prime = 0
    i = 1
    while (prime < number)
      i += 1
      prime += 1 if Prime.prime?(i)
    end
    i
  end
end
