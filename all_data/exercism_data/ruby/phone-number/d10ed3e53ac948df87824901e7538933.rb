class PhoneNumber
  def initialize(number)
    @number = number
  end

  def number
    return '0000000000' if @number =~ /[a-zA-Z]/
    @number = @number.gsub(/\D/, '')
    return '0000000000' if @number.length < 10
    return '0000000000' if @number.length > 11
    return '0000000000' if @number.length == 11 and !@number.start_with?('1')

    @number = @number[1..-1] if @number.length == 11 && @number.start_with?('1')
    @number
  end

  def area_code
    @number[0..2]
  end

  def to_s
    @number = number
    "(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..9]}"
  end
end
