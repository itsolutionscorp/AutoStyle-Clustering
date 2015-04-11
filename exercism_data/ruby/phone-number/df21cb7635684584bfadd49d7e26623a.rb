class PhoneNumber

  def initialize phonenumber
    @phonenumber = phonenumber
    modify_number!
  end

  def number
    @phonenumber
  end

  def area_code
    @phonenumber[0..2]
  end

  def to_s
    "(#{area_code}) #{@phonenumber[3..5]}-#{@phonenumber[6..9]}"
  end

  private

  def modify_number!
    remove_all_special_characters!
    if less_than_10_digits? or more_than_11_digits?
      @phonenumber = bad_number
    elsif equal_to_11_digit?
      if first_digit_equal_to_1?
        @phonenumber = @phonenumber[1..-1]
      else
        @phonenumber = bad_number
      end
    else
      @phonenumber
    end
  end

  def remove_all_special_characters!
    @phonenumber.gsub!(/[^0-9]/ ,'')
  end

  def less_than_10_digits?
    @phonenumber.length < 10
  end

  def equal_to_11_digit?
    @phonenumber.length == 11
  end

  def more_than_11_digits?
    @phonenumber.length > 11
  end

  def first_digit_equal_to_1?
    @phonenumber[0] == "1"
  end

  def bad_number
    "0000000000"
  end

end
