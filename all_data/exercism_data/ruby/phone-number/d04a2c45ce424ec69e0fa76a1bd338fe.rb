class PhoneNumber
  attr_reader :number, :area_code

  def initialize(number)
    @number = valid?(n = clean(number)) ? n : "0"*10
    @number = n[1, 10] if @number.length == 11
    @area_code = @number[0, 3]
  end

  def to_s
    "(#{number[0, 3]}) #{number[3, 3]}-#{number[6, 4]}"
  end

  private

  def clean(n)
    n.gsub(/[()-. ]/, '')
  end

  def valid?(n)
    n !~ /\D/ && ((n.length == 11 && n[0] == '1') || n.length == 10)
  end
end
