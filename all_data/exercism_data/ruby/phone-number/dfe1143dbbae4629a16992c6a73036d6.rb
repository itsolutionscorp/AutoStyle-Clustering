class PhoneNumber
  attr_accessor :number

  def initialize(input)
    @number = strip(input)
    drop_the_one!
    set_to_zeros! unless long_enough? && short_enough?
  end

  def area_code
    area_code = "#{@number[0..2]}"
  end

  def local_number
    "#{@number[3..5]}-#{@number[6..9]}"
  end

  def to_s
    "(#{area_code}) " + local_number
  end

  private
  def drop_the_one!
    if @number.length == 11 && @number[0] == "1"
      @number = @number[1..10]
    end
  end

  def strip(input)
    input.gsub(/[^[:alnum:]]/, "")
  end

  def long_enough?
    @number.length > 9
  end

  def short_enough?
    @number.length <= 10
  end

  def set_to_zeros!
    @number = "0000000000"
  end

end
