class PhoneNumber
  attr_reader :number

  INVALID = '0' * 10

  def initialize(num)
    # setter method is used to enforce consistency
    # but you must use self in initialize, why????
    self.number = num
  end

  def number=(num)
    @number = normalize(num)
    @number = INVALID unless valid?
  end

  def valid?
    number.length == 10 && /^[0-9]*$/.match(number)
  end

  def area_code
    return unless valid?
    number.slice(0, 3)
  end

  def local_number
    return unless valid?
    number.slice(3, 7)
  end

  def to_s
    "(#{area_code}) #{local_number.insert(3, '-')}"
  end

  private

  def normalize(num)
    num.gsub!(/\s|-|\(|\)|\./, '')
    if num.length == 11 && num.slice(0) == '1'
      num.slice(1, 10)
    else
      num
    end
  end
end
