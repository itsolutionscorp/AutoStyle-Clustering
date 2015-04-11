class Phone
  attr_reader :number, :area_code

  def initialize(number)
    @number = clean(number)
    @area_code = @number[0..2]
  end

  def clean(number)
    number.gsub!(/[^0-9]/, '')

    if number.size == 11 && number[0] == '1'
      number[1..11]
    elsif number.size != 10
      '0' * 10
    else
      number
    end
  end

  def to_s
    "(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..10]}"
  end
end
