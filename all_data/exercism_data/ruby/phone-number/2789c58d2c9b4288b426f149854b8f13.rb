class PhoneNumber
  attr_reader :number

  def initialize(num_str)
    @number = clean(num_str)
  end

  def clean(num_str)
    num_str.gsub!(/[^[0-9]]/, "")
    bad_number = "0000000000"
    case num_str.length
      when 10
        num_str
      when 11
        num_str[0] == '1' ? num_str[1..-1] : bad_number
      else
        bad_number
    end
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..9]}"
  end
end
