class Raindrops
  def self.convert(drop_number)
    answer = ""
    answer << "Pling" if Raindrops.divisible_check(drop_number, 3)
    answer << "Plang" if Raindrops.divisible_check(drop_number, 5)
    answer << "Plong" if Raindrops.divisible_check(drop_number, 7)
    answer = drop_number.to_s if answer == ""
    answer
  end

  def self.divisible_check(drop_number, divisor)
    drop_number % divisor == 0
  end

end
