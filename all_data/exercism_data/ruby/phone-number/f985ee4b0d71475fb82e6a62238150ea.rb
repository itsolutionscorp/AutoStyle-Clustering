class PhoneNumberTest < MiniTest::Unit::TestCase
  class PhoneNumber
    def initialize(number)
      @number = number
    end

    def number
      number = @number.gsub(/[^0-9a-z]/i, '')
      validates(number)

    end

    def validates(number)
      if digits(number) == 11 && number[0] == "1"
        number.slice(1..-1) # this gets rid of the superfilous 1 in the beginning.
      elsif digits(number) >= 11 && number[0] != "1" # if number of digits is more than 11 and no 1 in the beginning
        shows_an_error
      elsif digits(number) < 10 # if number of digits in the phone number less than 10
        shows_an_error
      else
       number
      end
    end


    def shows_an_error
      number = "0" * 10 # replaces the wrong number with ten zeros
    end

    def digits(number)
      number.length
    end

    def area_code
      number.slice(0..2)
    end


    def first_three_digits
      number.slice(3..5)
    end

    def last_four_digits
      number.slice(6..9)
    end

     def to_s
        "(#{area_code}) #{first_three_digits}-#{last_four_digits}"
      end
  end

end
