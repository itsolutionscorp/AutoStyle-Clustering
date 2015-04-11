class Raindrops
  def self.convert(number)
    output = ''
    non_prime_count = 0
    number % 3 == 0 ? output += 'Pling' : non_prime_count += 1
    number % 5 == 0 ? output += 'Plang' : non_prime_count += 1
    number % 7 == 0 ? output += 'Plong' : non_prime_count += 1
    output += number.to_s if non_prime_count == 3

    output
  end
end
