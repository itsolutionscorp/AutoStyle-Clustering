module Raindrops

  PRIMES_TAGS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    #starting from 2 cuz 2 is smallest possible prime
    answer = _convert(number, 2, [1])
    answer == '' ? number.to_s : answer
  end


  private

  def self._convert(number, iterator, acc)
    return self._parse_primes(acc) if iterator > number
    if number % iterator == 0
      value = number/iterator
      self._convert(value, iterator, acc.push(iterator))
    else
      self._convert(number, iterator+1, acc)
    end
  end

  def self._parse_primes(primes)
    primes.map { |p| PRIMES_TAGS[p] }.uniq.join('')
  end
end
#====
#def  convert(number)
    #i = number
    #primes = []
    #while i >= 0 
      #primes.push
    #end
#end
