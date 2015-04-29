class PhoneNumber
  def initialize(string)
    @string = string
 end

  def number
    parse
  end

  def area_code
    parse[0..2]
  end

  def to_s
    "(#{area_code}) #{exchange_code}-#{subscriber_number}"
  end

  def subscriber_number
    parse[6..9]
  end

  def exchange_code
    parse[3..5]
  end

  private

  def parse
    if @string.match(/[0-9a-zA-Z]{12,}?/)
      return zeros
    end
    string = @string.gsub(/[\W\D]/, '')
    if string.size == 11 && string[0] == "1"
      string = string[1..10]
    end
    if string.size != 10
      return zeros
    end
    string
  end


  def zeros
    "0" * 10
  end
end
