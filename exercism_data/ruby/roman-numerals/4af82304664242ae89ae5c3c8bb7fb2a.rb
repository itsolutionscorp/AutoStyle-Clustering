class Fixnum
  def to_roman
	  rome = self
	  roman_nums = {
    "M" => 1000, "CM"=> 900, "D" => 500, "CD" => 400,
    "C" => 100, "XC" => 90, "L" => 50, "XL" => 40,
    "X" => 10, "IX" => 9, "V" => 5,"IV" => 4,
    "I" => 1
		}
    roman_loop = ""
    roman_nums.each do |a|
      rome_letter_count = rome / a[1]
      roman_loop += a[0] * rome_letter_count
      rome -= a[1] * rome_letter_count
		  end
		roman_loop
	end
end
