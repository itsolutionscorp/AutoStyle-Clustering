class PhoneNumber
  def initialize(source)
    @number = source.gsub(/[\.\s()-]/, '')
    if @number.length == 11 && @number[0] == "1"
      @number = @number[1..-1]
    end
    if @number.length != 10 || @number =~ /\D/
      @number = "0" * 10
    end

    @area_code = @number[0...3]
    @exchange = @number[3...6]
    @local = @number[6...10]
  end

  attr_reader :number, :area_code, :exchange, :local

  def to_s
    "(%d) %d-%d" % [area_code, exchange, local]
  end
end
