module Raindrops
  module_function

  def convert(number)
    result = ""
    
    result << "Pling"     if number % 3 == 0
    result << "Plang"     if number % 5 == 0
    result << "Plong"     if number % 7 == 0
    result << number.to_s if result.empty?
    
    return result
  end
end
