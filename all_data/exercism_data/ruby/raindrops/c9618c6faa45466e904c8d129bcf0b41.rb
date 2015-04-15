class Raindrops

  def self.factorize(num)
    factors = []
    i = 2

    return [1] if(num < 2)

    while(i <= num)
      if(num % i == 0) then
        factors.push(i)
        num = (num / i).to_i
      else i += 1
      end
    end

    factors
  end

  def self.get_string(factors)
    result = ''
    factors.uniq.each { |factor| result += get_equivalent(factor) }
    result
  end

  def self.get_equivalent(num)
    raindrops = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }
    raindrops.has_key?(num) ? raindrops[num] : ''
  end

  def self.convert(num)
    result = get_string(factorize(num))
    result = "#{num}" if(result.length == 0)
    result
  end

end
