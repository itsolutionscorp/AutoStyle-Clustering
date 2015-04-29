class PhoneNumber

  BAD_NUMBER = ['0']*10

  def initialize(phone_number)
    @phone_number = phone_number.to_s
  end

  def number
    literals.join
  end

  def to_s
    "(#{area_code}) #{first_three}-#{last_four}"
  end

  def area_code
    literals.first(3).join
  end

  private

  def literals
    literals = @phone_number.scan(/\d/)
    return BAD_NUMBER if literals.length > 11 || literals.length < 10

    if literals.length == 11
      return BAD_NUMBER unless literals.first == '1'
      literals.slice! 0
    end

    literals
  end

  def first_three
    literals[3..5].join
  end

  def last_four
    literals.last(4).join
  end

end
