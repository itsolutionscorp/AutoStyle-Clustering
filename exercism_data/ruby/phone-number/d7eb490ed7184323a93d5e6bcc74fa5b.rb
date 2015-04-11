class PhoneNumber
  attr_reader :number

  Rex = [
    /^\((?<ac>\d{3})\)\s+(?<ex>\d{3})-(?<num>\d{4})$/,
    /^1?(?<ac>\d{3})\.?(?<ex>\d{3})\.?(?<num>\d{4})$/
  ]

  Invalid_Number = '0'*10

  def area_code
    "#{@rex[:ac]}"
  end

  def to_s
      "(#{@rex[:ac]}) #{@rex[:ex]}-#{@rex[:num]}"
  end

  def initialize num
    @rex = Rex.map { |i| num.match i }.detect{ |i| i }
    @number = if @rex.nil?
      "0"*10 
    else
      "#{@rex[:ac]}#{@rex[:ex]}#{@rex[:num]}"
    end
  end
end
