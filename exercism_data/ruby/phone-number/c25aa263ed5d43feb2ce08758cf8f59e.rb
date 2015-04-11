class PhoneNumber
  def initialize num
    @valid_num = parse(num)
  end

  def number
    @valid_num
  end

  def area_code
    @valid_num[0..2]
  end

  def to_s
    "(#{@valid_num[0..2]}) #{@valid_num[3..5]}-#{@valid_num[6..9]}"
  end

  private

  def parse num
    bad_num = '0000000000'
    input_num = num.gsub(/[\s\(\)-\.]/, '').to_i.to_s
    case input_num.length
    when 10
      input_num
    when 11
      input_num[0] == '1' ? input_num[-10..-1] : bad_num
    else
      bad_num
    end
  end
end
