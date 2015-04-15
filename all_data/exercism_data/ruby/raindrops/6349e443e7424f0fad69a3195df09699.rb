class Raindrops
  def convert(zahl)
    arr = %w[]
    divisor = 2
    zahlB = zahl
    while zahl > 1 do
      while zahl % divisor == 0 do
        if divisor == 3 and not arr.include?('Pling') then
          arr << "Pling"
        elsif divisor == 5 and not arr.include?('Plang') then
          arr << "Plang"
        elsif divisor == 7 and not arr.include?('Plong') then
          arr << "Plong"
        else
          divisor.to_s
        end
        zahl = zahl / divisor
      end
      divisor = divisor + 1
    end
    divisor = divisor - 1
    if arr.empty? then
      zahlB.to_s
    else
      arr * ""
    end
  end
end
