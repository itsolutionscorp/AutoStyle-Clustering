class PhoneNumber

  attr_reader :number, :area_code

  def initialize(str)
    @number, @area_code, @mid, @end = (str.gsub(/[^\d]/,'').gsub(/\A1(\d{10})\z/,"\\1").match(/\A(\d{3})(\d{3})(\d{4})\z/)||["0"*10,"0"*3,"0"*3,"0"*4]).to_a
  end

  def to_s
    "(#{area_code}) #{@mid}-#{@end}"
  end

end
