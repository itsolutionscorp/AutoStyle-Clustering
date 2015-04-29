class PhoneNumber
  module Builder
    extend self

    INVALID_NUMBER = '0000000000'

    def call(number)
      number = scrub_non_digits(number)
      number = drop_us_code(number)
      filter_invalid_numbers(number)
    end

    private

    def scrub_non_digits(number)
      number.gsub(/\D/, '')
    end

    def drop_us_code(number)
      number.size == 11 && number.start_with?('1') ? number[1..-1] : number
    end

    def filter_invalid_numbers(number)
      number.size == 10 ? number : INVALID_NUMBER
    end
  end

  attr :number

  def initialize(raw)
    @number = Builder.call(raw)
  end

  def area_code
    number.slice(0, 3)
  end

  def to_s
    number.gsub(/(\d{3})(\d{3})(\d{4})/, '(\1) \2-\3')
  end
end
