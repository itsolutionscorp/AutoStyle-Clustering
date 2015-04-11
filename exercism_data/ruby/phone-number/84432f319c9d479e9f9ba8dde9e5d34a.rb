class PhoneNumber

  attr_reader :raw_num

  def initialize(raw_num)
    @raw_num = raw_num
    prepare_number
  end

  def area_code
    number[0,3]
  end

  def number
    raw_num
  end

  def to_s
    "(" + area_code + ") " + number[3,3] + "-" + number[6,4]
  end

  private

  def prepare_number
    clean
    remove_leading_one
    set_to_zeros unless valid? 
  end

  def clean
    @raw_num = raw_num.scan(/[0-9]/).join 
  end

  def set_to_zeros
    @raw_num = "0000000000"
  end

  def remove_leading_one
    if raw_num.length == 11 && raw_num.start_with?("1")
      @raw_num = raw_num[1..-1]
    else
      raw_num
    end
  end

  def valid?
    raw_num.length == 10
  end

end
