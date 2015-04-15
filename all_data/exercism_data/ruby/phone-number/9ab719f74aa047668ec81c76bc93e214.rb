class PhoneNumber
  NULL_PHONE_NUMBER = "0" * 10

  def initialize(source)
    @source = source.gsub(/[^0-9a-zA-Z]/, "")
    remove_country_code
  end

  def number
    valid? ? source : NULL_PHONE_NUMBER
  end

  def area_code
    number[0..2]
  end

  def prefix
    number[3..5]
  end

  def line
    number[6..9]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line}"
  end

private

  attr_accessor :source

  def numeric?
    source.to_i.to_s == source
  end

  def valid?
    numeric? && source.length == 10
  end

  def remove_country_code
    if source.length == 11 && source.start_with?('1')
      @source = source[1..-1]
    end
  end  
end
