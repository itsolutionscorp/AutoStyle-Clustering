class PhoneNumber

  def initialize phone_number
    @phone_number = phone_number
  end

  def number
    strips_non_numbers(@phone_number)
    removes_long_distance_code(@phone_number)
    is_valid?(@phone_number)
    @phone_number
  end

  def to_s
    if @phone_number.length == 10 || removes_long_distance_code(@phone_number)
      @phone_number = "(" + area_code + ")" + " " + @phone_number[3..5] + "-" + @phone_number[6..10]
    end
  end

  def strips_non_numbers phone_number
    @phone_number = phone_number.gsub(/[\(\)\.\[\]\-\s+]/,"")
  end

  def removes_long_distance_code phone_number
    if phone_number.length == 11 && phone_number[0] == "1"
      @phone_number.slice!(0)
    end
  end

  def is_valid? phone_number
    if phone_number[/[^0-9]/] != nil || phone_number.length <= 9 || phone_number.length >= 11
      @phone_number = '0000000000'
    end
  end

  def area_code
    @phone_number[0..2]
  end
end
