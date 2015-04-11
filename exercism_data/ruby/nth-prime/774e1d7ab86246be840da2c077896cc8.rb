require 'prime'

class Prime

  def self.nth(x)

    count = 3
    index = 1 # start at 1 since we are skipping the number 2 (the first prime number)

    if x == 2
      1
    else
      while true
        index += 1 if Prime.prime?(count)
        return count if index == x
        count += 2 # only test odd numbers
      end
    end
  end
end
