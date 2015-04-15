class Prime

  def self.extendPrimeList (list)
    return [2] if list.length==0
    number = list.last.next
    l=list.length
    while list.length==l
      isprime = TRUE
      list.each{|x|
        if number % x ==0
          isprime=FALSE
          break
        end
      }
      if isprime==TRUE
        list<<number
      else
        number += 1
      end
    end
    list
  end

  def self.nth(n)
  raise ArgumentError if n<=0
    primeList = []
    while primeList.length<n
      primeList = extendPrimeList(primeList)
    end
    primeList.last
  end
end
