# class to validate phone numbers
class PhoneNumber
  BAD_NUM = '0000000000'

  def initialize(tel_num)
    @tel_num = tel_num
    clean
    validate
  end

  def number
    @tel_num
  end

  def area_code
    slice 0
  end

  def to_s
    "(#{area_code}) #{slice(3)}-#{slice 6, 4}"
  end

  private

  def slice(start, chars=3)
    @tel_num.slice(start, chars)
  end

  def length
    @tel_num.length
  end

  def clean
    @tel_num.gsub! /[^0-9A-Za-z]/, ''
    @tel_num = @tel_num[1, length] if length == 11 && @tel_num[0] == '1'
  end

  def validate
    @tel_num = BAD_NUM unless length == 10 && @tel_num !~ /[a-zA-z]/
  end
end
