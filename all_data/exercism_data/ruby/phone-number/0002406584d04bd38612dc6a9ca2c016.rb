class Phone   

  def initialize raw_number    
    set_defaults
    store clean raw_number
  end

  def number
     area_code + exchange_code + subscriber_number
  end

  def area_code
    @number_info[:Area_Code]
  end

  def exchange_code
    @number_info[:Exchange_Code]
  end

  def subscriber_number
    @number_info[:Subscriber_Number]
  end

  def to_s
    "(#{area_code}) #{exchange_code}-#{subscriber_number}"
  end
  
  private
  def clean number
    number.scan(/\d+/).join
  end

  def store number
    return unless number =~ /\A (1?) (\d{3}) (\d{3}) (\d{4}) \z/x

    @number_info[:Country_Code] = $1
    @number_info[:Area_Code] = $2
    @number_info[:Exchange_Code] = $3
    @number_info[:Subscriber_Number] = $4
  end

  def set_defaults
    @number_info = {:Country_Code => "", :Area_Code => "000", :Exchange_Code => "000", :Subscriber_Number => "0000"}    
  end
  
end
