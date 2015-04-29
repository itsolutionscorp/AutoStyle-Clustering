class PhoneNumber

  def initialize(number)
    @number = number
  end

  def number
    remove_one
    if trim.length <= 9
      '0000000000'
    elsif trim.length >=11 && @number[0] != 1
      '0000000000'
    else
      trim
    end
  end

  def trim
    @number.tr('^0-9', '')
  end

  def remove_one
    if (/\A[1]/).match(@number) != nil
      @number = @number[1..-1]
    end
  end

  def area_code
    trim[0..2]
  end

  def to_s
    remove_one
    "(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..9]}"
  end
end
