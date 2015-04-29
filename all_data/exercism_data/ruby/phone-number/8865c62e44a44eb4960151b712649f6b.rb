class PhoneNumber
  attr_reader :number

  ERROR = '0' * 10

  def initialize(phone_number)
    @number = make_10_digit(phone_number)
  end

  def to_s
    "(#{area_code}) #{exchange}-#{subscriber}"
  end

  def area_code
    @number[0..2]
  end

  def exchange
    @number[3..5]
  end

  def subscriber
    @number[6..9]
  end

private

  def make_10_digit(phone)
    return ERROR if phone =~ /[[:alpha:]]/

    phone = collect_digits(phone)
    phone = remove_1_prefix(phone)
    return ERROR if phone.length != 10

    phone
  end

  def collect_digits(phone)
    phone.gsub(/\D/, '')
  end

  def remove_1_prefix(phone)
    return phone[1..10] if phone.length == 11 && phone[0] == '1'
    phone
  end
end
