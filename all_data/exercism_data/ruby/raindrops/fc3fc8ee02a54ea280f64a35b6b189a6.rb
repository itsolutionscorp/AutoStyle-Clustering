module Raindrops

  CONVERSIONS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }



  # 58% more efficient
  def self.convert(number)
    size = CONVERSIONS.size
    keys = CONVERSIONS.keys
    string = ""
    i = 0
    while i < size
      factor = keys[i]
      string += CONVERSIONS[factor] if number % factor == 0
      i+=1
    end
    string.empty? ? number.to_s : string
  end

end

  #def self.convert_mikelikesbikes(n)
    #s = CONVERSIONS.each_with_object("") do |(factor,sound), s|
      #s << sound if n % factor == 0
    #end
    #s.empty? ? n.to_s : s
  #end

  ##slowest and breaks on big numbers because of stack size, need to tune ruby vm
  #def self.convert_recursive(number)
    ##starting from 2 cuz 2 is smallest possible prime
    #answer = _convert(number, 2, [1])
    #answer == '' ? number.to_s : answer
  #end

  #private

  #def self._convert(number, iterator, acc)
    #return self._parse_primes(acc) if iterator > number
    #if number % iterator == 0
      #value = number/iterator
      #self._convert(value, iterator, acc.push(iterator))
    #else
      #self._convert(number, iterator+1, acc)
    #end
  #end

  #def self._parse_primes(primes)
    #primes.map { |p| CONVERSIONS[p] }.uniq.join('')
  #end
