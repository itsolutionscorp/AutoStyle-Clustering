# Resubmitted when I realized I was using `prefix` in two ways! Doh!
class Phone
  DIALABLE_LENGTH = 10
  INVALID_NUMBER = '0' * 10
  USA_INTERNATIONAL_CODE = '1'
  WITH_INTERNATIONAL_CODE_LENGTH = 1 + DIALABLE_LENGTH

  attr_accessor :number

  def initialize(number)
    self.number = number
    verify_number!
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private

  def has_usa_international_code?
    number.length == WITH_INTERNATIONAL_CODE_LENGTH && number[0] == USA_INTERNATIONAL_CODE
  end

  def ignore_usa_international_code!
    self.number = number[1..-1] if has_usa_international_code?
  end

  def line_number
    number[6..-1]
  end

  def mark_invalid!
    self.number = INVALID_NUMBER
  end

  def prefix
    number[3..5]
  end

  def remove_non_digits!
    number.gsub! /[^0-9]/, ''
  end

  def undialable?
    number.length != DIALABLE_LENGTH
  end

  def verify_number!
    remove_non_digits!
    ignore_usa_international_code!
    mark_invalid! if undialable?
  end
end
