class Binary
  attr_reader :string
  def initialize binary_string
    @string = clean_string binary_string
  end
  
  def to_decimal
    @decimal ||= string.split('').reduce(0) {|dec,bit| dec = dec*2 + bit.to_i}
  end
  
  private

  def clean_string string
    return string if valid_binary? string
    "0"
  end
  
  def valid_binary? string
    !((string =~ /^[10]+$/).nil?)
  end
end
