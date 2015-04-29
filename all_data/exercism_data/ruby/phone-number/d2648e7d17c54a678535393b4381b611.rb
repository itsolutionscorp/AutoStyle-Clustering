require 'pry-byebug'
class PhoneNumber
  def initialize(phone_number)
    @phone_number = phone_number
  end

  def number
    if less_than_10_digits || has_letters || when_11_digits || when_12_digits_and_first_is_1 || when_10_digits_with_extra_letters
     '0000000000'
    else
      eleven_digits_and_first_is_1
      @phone_number.gsub(/\D/, "")
    end
  end

  def area_code
    @phone_number.slice!(0..2)
  end
  
  def to_s
    if @phone_number.length == 11
      @phone_number.slice!(0)
    end
    a_code = @phone_number.slice!(0..2) 
    pre = @phone_number.slice!(0..2)
    "(#{a_code}) #{pre}-#{@phone_number}"  
  end

  private

  def less_than_10_digits
    @phone_number.length <10
  end

  def has_letters
    @phone_number.gsub(/[A-z]/, "*").include? "*"
  end

  def eleven_digits_and_first_is_1
    if @phone_number.length == 11 && @phone_number.start_with?("1")
      @phone_number[0] = ""
    end
  end

  def when_11_digits
    @phone_number.length == 11 && @phone_number.start_with?("1") == false  end

  def when_12_digits_and_first_is_1
    @phone_number.start_with?("1") && @phone_number.length == 12
  end

  def when_10_digits_with_extra_letters
    @phone_number.gsub(/[A-z]/, "").length == 10
  end
end
