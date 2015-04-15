require 'prime'
class Prime
  #def self.nth(n)
    #raise ArgumentError if n == 0
    #Prime.first(n)[n-1]
  #end

  def nth(n)
    raise ArgumentError if n == 0
    current = 1; count = 1
    while count <= n
      current += 1; prime = true
      current.downto(1).each do |i|
        if current % i == 0 && i != 1 && i != current
          prime = false
          break
        end
      end
      count += 1 if prime
    end
    return current
  end
end
