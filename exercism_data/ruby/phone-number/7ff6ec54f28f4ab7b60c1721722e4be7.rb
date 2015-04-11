class Phone
  attr_reader :number, :area_code

  def initialize(number)
    @number = normalize(clean(number))
    @area_code = @number[0..2]
  end

  def clean(number)
    number.gsub(/[^0-9]/, '')
  end

  def normalize(number)
    if number.size == 11 && number[0] == '1'
      number[1..11]
    elsif number.size == 10
      number
    else
      '0' * 10
    end
  end

  def to_s
    "(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..10]}"
  end
end
