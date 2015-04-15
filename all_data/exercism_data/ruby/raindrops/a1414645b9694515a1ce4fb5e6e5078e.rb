class Raindrops
  def self.convert(number)
    divisors = { 3 => "Pling", 5 => "Plang", 7 => "Plong"}

    first_string = strings(divisors, number)
    first_string == "" ? number.to_s : first_string
  end

  def self.strings(divisors, number)
    divisors.reduce("") do |string, num|
      string += divisors[num[0]] if number % num[0] == 0
      string
    end
  end
end
