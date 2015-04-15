class Phone

  def initialize(number)
    @number = number.scan(/\d/).join
    trim_country_code
  end

  def number
    valid_length? ? @number : '0'*10
  end

  def valid_length?
    length == 10
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end

  private

  def trim_country_code
    if length == 11 && @number.start_with?('1')
      @number = @number[1..10]
    end
  end

  def length
    @number.length
  end

end
