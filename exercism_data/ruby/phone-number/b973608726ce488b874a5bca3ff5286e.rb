class PhoneNumber

  attr_reader :phone_number

  def initialize phone_number 
    @phone_number = phone_number
  end

  def number
    parser
    if is_valid?
      convert_to_10_digits_number
      phone_number
    else
      '0000000000'
    end
  end
  
  def area_code
    phone_number[ 0, 3 ]
  end 

  def to_s
    convert_to_10_digits_number
    "(#{ phone_number[ 0, 3] }) #{ phone_number[3, 3] }-#{ phone_number[ 6,4 ] }"
  end

private

  attr_writer :phone_number

  def parser
    phone_number.gsub!( /\W/, '' )
  end

  def has_word_character?
     !!( phone_number =~ /[\D]/ )
  end

  def is_valid?
    ( phone_number.size == 10 or ( phone_number.size == 11 and phone_number.start_with?( '1' ) ) ) and not has_word_character?
  end

  def convert_to_10_digits_number
    if phone_number.size == 11
      self.phone_number = phone_number[ 1..-1 ]
    end
  end

end
