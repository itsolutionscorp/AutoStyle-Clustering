require 'Prime'

class Raindrops

  def self.convert(user_input_number)
#    prime_results = Prime.prime_division(user_input_number).map { |array| array[0] }

    response = [3,5,7].zip(['Pling','Plang','Plong']).map { | prime, onomatopoeia | onomatopoeia if Prime.prime_division(user_input_number).map { |array| array[0] }.include? prime }.join

    response.empty? ? user_input_number.to_s : response
  end

end
