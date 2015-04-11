class Phone
  def initialize(input)
    @phone_no=input
  end
  attr_reader :phone_no
  def number
    is_valid?
  end
  def area_code
    phoneno=is_valid?
    if  phoneno !='0000000000'
      phoneno[0, 3]
    else
      phoneno
    end
  end
  def to_s
  phoneno=is_valid?
  phoneno.insert(0,'(')
  phoneno.insert(4,')')
  phoneno.insert(5,' ')
  phoneno.insert(9,'-')
  return phoneno
  end
  private
  def is_valid?
    phoneno=phone_no.scan(/\d+/).join
    case phoneno.length
      when 10
        phoneno
      when 11
        if phoneno.start_with?('1')
          phoneno.byteslice(1,10)
        else
          "0000000000"
        end
      else
        "0000000000"
    end
  end


    
end 
