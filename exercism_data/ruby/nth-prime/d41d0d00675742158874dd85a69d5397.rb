class Prime

  @@list = [2];
  def self.nth(num)
    raise ArgumentError if num == 0
    n = 3;
    while(@@list.length < num)
      divisible = false
      for i in 2...n
        if(n % i == 0)
          divisible = true
          break
        end
      end

      if(divisible == false)
        @@list.push(n)
      end
      n += 1
    end
    return @@list[num-1];
  end

end
