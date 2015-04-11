class PhoneNumber

  def initialize(phone_number)
    phone_number.gsub!(/[()-.\s]/,"").to_s
    @phone_number = phone_number
  end

  def number
    return '0000000000' if contains_characters?

    unless (@phone_number.length == 10 || @phone_number.length == 11)
      return '0000000000'
    end

    if @phone_number.length == 11
      return @phone_number[1,10] if @phone_number.start_with?('1')
      return '0000000000'
    end
    
    @phone_number
  end

  def to_s
    "(#{number[0,3]}) #{number[3,3]}-#{number[6,4]}"
  end

  def area_code
    number[0,3]
  end

  private

  def contains_characters?
    @phone_number.match(/[a-eA-Z]/)
  end

end
