class PhoneNumber
  INVALID_NUMBER = "0000000000"
  FORMAT_STRING = "(%s) %s-%s"

  def initialize number
    @number = number
    @number_array = number.scan(/\d/)
    strip_special_number
  end

  def strip_special_number
    @number_array.delete_at(0) if size_array == 11 && @number_array[0] == "1"
  end
  
  def size_array
    @number_array.count
  end

  def number
    return INVALID_NUMBER unless is_valid
    @number_array.join
  end

  def area_code
      @number_array[0...3].join
  end

  def is_valid
    size_array == 10 && !(@number =~ /[a-zA-Z]+/)
  end
  
  def to_s
      FORMAT_STRING % [area_code, @number_array[3...6].join, @number_array[6...10].join ]
  end

end
