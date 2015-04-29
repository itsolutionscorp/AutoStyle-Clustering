class Raindrops
  def convert(number)
    drops = %w[]
    divisor = 2
    numberB = number
    while number > 1 do
      while number % divisor == 0 do
        if number % 3 == 0 and not drops.include?('Pling') then
          drops << "Pling" 
        elsif number % 5 == 0 and not drops.include?('Plang') then
          drops << "Plang"
        elsif number % 7 == 0 and not drops.include?('Plong') then
          drops << "Plong"
        else
          divisor.to_s
        end
        number = number / divisor
      end
      divisor = divisor + 1
    end
    divisor = divisor - 1
    if drops.empty? then
      numberB.to_s
    else
      drops * ""
    end
  end
end
