class Luhn

  def initialize(account_number)
    @account_number =  account_number.to_s.split("")
  end

  def addends
    double_every_second_digit(account_number)
  end

  def checksum
    addends.inject(:+)
  end

  def valid?
    checksum.to_s[-1] == "0"
  end

  def self.create(number)
    add_check_digit(number)
  end

  private 

  attr_reader :account_number

  def self.add_check_digit(number)
    range_of_valid_numbers = (0..9)

    unless Luhn.new(number).valid? 
      number = number.to_s 
      range_of_valid_numbers.each do |num|
        number << num.to_s
        break if Luhn.new(number.to_i).valid?
        number.slice!(-1)
      end
    end
    number.to_i
  end


  def double_every_second_digit(numbers)
    reverse_numbers = numbers.reverse

    reverse_numbers.map.with_index do |number, index|
      if index % 2 == 0
        number.to_i
      elsif number.to_i * 2 > 9 && index % 2 != 0
        (number.to_i * 2) - 9
      else
        number.to_i * 2
      end
    end.reverse
  end

end
