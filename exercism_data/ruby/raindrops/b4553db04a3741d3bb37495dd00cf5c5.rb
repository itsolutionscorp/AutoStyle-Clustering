require 'Prime'

class Raindrops

  def self.convert(user_input_number)

    prime_results = Prime.prime_division(user_input_number).map do |array|
      array[0]
    end

    response = ""
    if prime_results.include? 3
      response << 'Pling'
    end

    if prime_results.include? 5
      response << 'Plang'
    end

    if prime_results.include? 7
      response << 'Plong'
    end

    if response == ""
      response = user_input_number.to_s
    end

    response
  end

end
