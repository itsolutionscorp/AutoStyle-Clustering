require 'pry-byebug'

class PhoneNumber
  def initialize(numbers)
    @numbers = numbers
  end

  def number
    if @numbers =~ /[A-z]/ || @numbers.length < 10 || (@numbers.length == 11 && @numbers.start_with?("1") == false) || (@numbers.length == 12 && @numbers.start_with?("1")) || (@numbers =~ /[A-z]/ && @numbers.gsub(/\D/, '').length == 10)
    '0000000000'
    else
      cleaner
    end
  end

  def area_code
    @numbers.slice(0..2)
  end

  def to_s
    if @numbers.length == 11
      @numbers.slice!(0)
    end
   a_code = @numbers.slice(0..2)
   pre = @numbers.slice(3..5)
   suf = @numbers.slice(6..9)
   "(#{a_code}) #{pre}-#{suf}"
  end

  private

  def cleaner
    @numbers.gsub!(/\D/, '')
    #binding.pry
    puts ""
    if @numbers.length == 11 && @numbers.start_with?("1")
        @numbers.slice!(0)
    end
    @numbers
  end
end

number = PhoneNumber.new('456.123.7890').number
