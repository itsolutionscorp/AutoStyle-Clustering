class Raindrops

  def self.convert number
    result_string=""

    result_string << "Pling" if number.modulo(3).zero? 
    result_string << "Plang" if number.modulo(5).zero? 
    result_string << "Plong" if number.modulo(7).zero? 

    return result_string unless result_string.eql? ""
    return number.to_s
  end
  
end
