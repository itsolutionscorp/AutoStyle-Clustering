class Raindrops
  def self.convert(number)
    raindrop_speak = ""
    raindrop_speak << "Pling" if number % 3 == 0
    raindrop_speak << "Plang" if number % 5 == 0
    raindrop_speak << "Plong" if number % 7 == 0
    return number.to_s if raindrop_speak.empty?
    raindrop_speak
  end
    # factors = []
    # old_number = number
    # while true
    #   found = false
    #   (2..Math.sqrt(number)).each do |n|
    #     if number %n == 0
    #       factors << n
    #       number = number / n
    #       found = true
    #       next
    #     end
    #   end
    #   if !found
    #     factors << number
    #     break
    #   end
    # end

    # return old_number.to_s unless ( factors & [3,5,7] ).any?
    # raindrop_speak = ""
    # raindrop_speak << "Pling" if factors.include? 3
    # raindrop_speak << "Plang" if factors.include? 5
    # raindrop_speak << "Plong" if factors.include? 7
    # raindrop_speak
  # end
end
