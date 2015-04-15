class PhoneNumber
  def initialize(number)
    @number = number
  end

  def number
    letters_arr = ('a'..'z').to_a
    letters = false
    num_digits = 0
    digits = ""
    @number.each_char do |chr|
      if letters_arr.include?(chr.downcase)
        letters = true
      end

      if chr == "0" || chr.to_i != 0
        digits += chr
        num_digits += 1
      end
    end

    if num_digits == 11 && digits[0] == "1"
      return digits[1...digits.length]
    elsif num_digits != 10 || letters || num_digits == 9
      return "0" * 10
    else
      return digits
    end
  end

  def area_code
    self.number[0...3]
  end

  def to_s
    "(" + self.area_code + ") " + self.number[3..5] + "-" + self.number[6..10]
  end
end
