class PhoneNumber
  def initialize(input_number)
    @input_number = input_number
  end

  def number
    valid? ? ten_digit_numeric : '0' * 10
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  def area_code
    number[0..2]
  end

  private

  def prefix
    number[3..5]
  end

  def line_number
    number[6..9]
  end

  def numeric
    @input_number.gsub(/[^\d]/, '')
  end

  def ten_digit_numeric
    numeric.match(/.{10}$/)[0]
  end

  def valid?
    return false if @input_number =~ /[a-zA-Z]/

    case numeric
    when ->(n) { !((10..11).include?(n.length)) }
      false
    when ->(n) { n.length == 11 && !n.start_with?('1') }
      false
    else
      true
    end
  end
end
