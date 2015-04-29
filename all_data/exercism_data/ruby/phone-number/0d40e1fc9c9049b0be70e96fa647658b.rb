class PhoneNumber
  attr_reader :number
  
  INVALID_NUMBER = "0000000000"

  def initialize(phone_number)
    @number = phone_number
    clean!
    validate!
  end

  def area_code
    @number[0...3]
  end

  def to_s
    "(#{@number[0...3]}) #{@number[3...6]}-#{@number[6..-1]}"
  end

  def clean!
    @number = @number.gsub(/[^0-9a-bA-B]/, "")
    if starts_with_1?
      @number = @number[1..11]
    end
  end

  def validate!
    @number = INVALID_NUMBER if has_letters? || invalid_number_of_digits?
  end

  private

  def has_letters?
    @number =~ /[a-bA-B]/
  end

  def invalid_number_of_digits?
    @number.size != 10
  end

  def starts_with_1?
    @number.size == 11 && @number.start_with?("1")
  end
end
