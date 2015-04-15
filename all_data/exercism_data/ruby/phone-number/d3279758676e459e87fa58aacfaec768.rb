class Phone   

  NUMBER_PATTERN = /\A(?<#{:country_code}>          1?)
                      (?<#{:area_code}>          \d{3}) 
                      (?<#{:exchange_code}>      \d{3}) 
                      (?<#{:subscriber_number}>  \d{4}) \z/x
  
  def initialize raw_number    
    set_defaults
    parse clean raw_number
  end

  def number
     area_code + exchange_code + subscriber_number
  end

  def area_code
    number_info[:area_code]
  end

  def exchange_code
    number_info[:exchange_code]
  end

  def subscriber_number
    number_info[:subscriber_number]
  end

  def to_s
    "(#{area_code}) #{exchange_code}-#{subscriber_number}"
  end
  
  private
  
    def clean number
      number.scan(/\d+/).join
    end

    def parse number
      return unless number =~ NUMBER_PATTERN

      number_info.keys.each {|key| number_info[key] = $~[key]}
    end

    def set_defaults
      self.number_info = {:country_code => "", :area_code => "000", :exchange_code => "000", :subscriber_number => "0000"}    
    end

    attr_accessor :number_info      

end
