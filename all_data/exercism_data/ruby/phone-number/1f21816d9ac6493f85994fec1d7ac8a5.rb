class PhoneNumber

  def initialize(input)
    @number = extract_number(input)
  end

  def number
    @number
  end

  def area_code
    @number.slice(0, 3)
  end

  def to_s
    "(#{area_code}) #{exchange}-#{suffix}"
  end

  private

  def extract_number(input)
    if input == input.upcase && input == input.downcase
      input = input.gsub(/\D+/, '')
      if input.length == 10
        input
      elsif input.length == 11 && valid_country_code?(input)
        remove_country_code(input)
      else
        bad_number
      end
    else
      bad_number
    end
  end

  def valid_country_code?(input)
    input.start_with?('1')
  end

  def remove_country_code(input)
    input.slice(1..-1)
  end

  def bad_number
    '0'*10
  end

  def exchange
    @number.slice(3, 3)
  end

  def suffix
    @number.slice(6, 4)
  end
end
