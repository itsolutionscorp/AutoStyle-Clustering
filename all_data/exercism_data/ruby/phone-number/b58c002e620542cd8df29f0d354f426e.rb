class PhoneNumber
  def initialize(input)
    @input = input
  end

  def number
    number = @input.gsub(/[^\d+]/, '')
    if valid_number?(number)
      remove_country_code(number)
    else
      '0' * 10
    end
  end

  def area_code
    remove_country_code(number).slice(0, 3)
  end

  def to_s
    num    = remove_country_code(number)
    first  = num[3,3]
    second = num[6,4]
    "(#{area_code}) #{first}-#{second}"
  end

  private

  def valid_number?(num)
    num.length == 10 ||
    (num.length == 11 && num.start_with?('1'))
  end

  # Slice off leading 1 if present
  def remove_country_code(num)
    num.length == 11 ? num[1..-1] : num
  end
end
