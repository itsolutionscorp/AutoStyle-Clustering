class PhoneNumber

  DEFAULT_NUMBER = "0000000000"

  def initialize(raw_number)
    @number = sanitize_number raw_number
  end

  def number
    @number
  end

  def area_code
    @number.slice(0, 3)
  end

  def to_s
    "(%3d) %3d-%4d" % [self.area_code, @number.slice(3,3), @number.slice(6,4)]
  end

  private
  def sanitize_number number
    return DEFAULT_NUMBER if number =~ /[^ \.\d\(\)\-]/

    number.gsub!(/\D/, "")
    return DEFAULT_NUMBER if number.length < 10 || number.length > 11

    if number.length == 11
      if number.start_with? "1"
        number[0] = ""
      else
        return DEFAULT_NUMBER
      end
    end

    return number
  end

end
