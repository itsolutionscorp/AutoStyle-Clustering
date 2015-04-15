class PhoneNumber

  attr_accessor :number

  def initialize(number)
    @number = valid_format?(number) ? clean(number) : '0000000000'
  end

  def to_s
    "(#{area_code}) #{@number[3..5]}-#{@number[6..9]}"
  end

  def area_code
    @number[0..2]
  end

  private

    def valid_format?(number)
      number =~ /^\D*1?\D*\d{3}\D*\d{3}\D*\d{4}\D*$/
    end

    def clean(number)
      number.gsub(/\D/, '')[-10..-1]
    end
end
