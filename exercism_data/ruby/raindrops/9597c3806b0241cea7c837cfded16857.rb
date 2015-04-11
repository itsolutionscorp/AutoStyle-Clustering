module Raindrops
  module_function
  def convert(number)
    response = ""
    [3, 5, 7].each do |factor|
      response << response_for(factor) if  divisible_by?(factor, number)
    end
    response.empty? ? number.to_s : response
  end

  def divisible_by?(factor, number)
    number % factor == 0
  end

  def response_for(number)
    responses = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    responses.default = ""
    responses[number]
  end
end
