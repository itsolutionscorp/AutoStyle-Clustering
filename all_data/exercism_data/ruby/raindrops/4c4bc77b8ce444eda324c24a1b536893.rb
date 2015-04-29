class Raindrops
  def self.convert(integer)
    convert_to_string(prime_factors(integer), integer)
  end

  private

  def self.prime_factors(integer, n=2, arr = [])
    return [integer] if integer <= 1
    if integer % n == 0
      arr << n
      integer = integer/n
    else
      n += 1
    end

    prime_factors(integer, n, arr)
    arr
  end

  def self.convert_to_string(prime_numbers, integer)
    string = ''
    intersection = prime_numbers & [3, 5, 7]
    return integer.to_s if intersection.empty?
    raindrop_speak(intersection)
  end

  def self.raindrop_speak(intersection)
    hash = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    intersection.map { |i| hash[i] }.join
  end
end
