class Binary
  
  def initialize(binary_string)
    @bstr = binary_string
  end
  
  def to_decimal
    return 0 unless /\D/.match(@bstr) == nil #/\D/ - A non-digit character ([^0-9])
    decimal= 0
    @bstr.split("").reverse.each_with_index do |n, i|
      decimal+=n.to_i*2**i
    end  
    decimal
  end    
  
end  
