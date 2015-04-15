require 'prime'
class Prime

  def self.my_nth(n)
    raise ArgumentError if n == 0
    arr = [2, 3]

    counter = 5
    while arr.size < n
      if counter % 3 != 0
        tmp = 4
        while tmp**2 <= counter
          break if counter % tmp == 0
          tmp += 1
        end
        arr << counter if tmp**2 > counter
      end
      counter += 2
    end
    arr[n-1]
  end
  
  def self.nth(n)
    raise ArgumentError if n == 0
    (Prime.first(n)).last
  end
end
