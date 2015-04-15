class Fixnum
  INTS = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
  NUMS = %w(M CM D CD C XC L XL X IX V IV I)

  def to_roman
    num = self 
    result = ""
    INTS.each_with_index do |int, ix|
      count = num / int 
      result << NUMS[ix] * count
      num -= int * count
    end 
    result
  end
end
