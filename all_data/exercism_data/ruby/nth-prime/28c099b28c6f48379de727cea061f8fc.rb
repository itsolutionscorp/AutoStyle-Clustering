require 'prime'

class Prime

  def self.nth(x)

    count = 3
    index = 1 

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
