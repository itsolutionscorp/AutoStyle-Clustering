class Raindrops
  def self.convert(input)
    output=""
    output=output+"Pling" if input%3==0
    output=output+"Plang" if input%5==0
    output=output+"Plong" if input%7==0

    return input.to_s if output==""
    output
  end

end
