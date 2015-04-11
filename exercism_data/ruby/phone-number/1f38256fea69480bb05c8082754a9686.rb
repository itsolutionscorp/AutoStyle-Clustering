class PhoneNumber
  attr_reader :number

  def initialize(initial)
    @number = cleanup(initial)
  end

  def area_code
    number[0..2]
  end

  def prefix
    number[3..5]
  end

  def postfix
    number[6..9]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{postfix}"
  end

  private

  def cleanup(initial)
    clean = initial.gsub(/[^0-9]/, '')

    if clean.length == 11 && clean[0] == '1'
      clean = clean[1..-1]
    end

    clean.length == 10 ? clean : '0' * 10
  end
end
