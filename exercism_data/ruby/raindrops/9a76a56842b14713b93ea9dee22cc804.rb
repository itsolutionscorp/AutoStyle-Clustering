require 'Prime'

class Raindrops

  def self.convert(user_input_number)

    prime_results = Prime.prime_division(user_input_number).map do |array|
      array[0]
    end

    response = ""
    for prime, onomatopoeia in [3,5,7].zip(['Pling','Plang','Plong']) do #| prime, onomatopoeia |
      if prime_results.include? prime
        response << onomatopoeia
      end
    end

    response.empty? ? user_input_number.to_s : response

  end

end