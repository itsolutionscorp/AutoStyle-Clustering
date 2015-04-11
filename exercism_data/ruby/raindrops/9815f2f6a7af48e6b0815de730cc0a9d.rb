class Raindrops
  def self.convert(number)
    string = ''
    string << 'Pling' if prime_factor? number, 3
    string << 'Plang' if prime_factor? number, 5
    string << 'Plong' if prime_factor? number, 7
    string << number.to_s if string == ''
    string
  end
  
  def self.prime_factor?(number, desired_factor)
    (number % desired_factor).zero?
  end
end
