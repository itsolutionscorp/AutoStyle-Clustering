class Raindrops

  def self.data
    { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
  end

  def self.has_no_prime_factor?(number)
    data.keys.each { |prime| return false if number % prime == 0 }
  end

  def self.convert(number)
    temp_string = ''

    return number.to_s if has_no_prime_factor?(number)

    data.each { |prime, string| temp_string << string if number % prime == 0 }

    temp_string
  end
end
