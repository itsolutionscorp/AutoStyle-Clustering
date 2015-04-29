class Raindrops
  def self.convert number
    result_string = ""
    if number%3 == 0
      result_string += 'Pling'
    end
    if number%5 == 0
      result_string += 'Plang'
    end
    if number%7 == 0
      result_string += 'Plong'
    end
    if result_string.empty?
      result_string = number.to_s
    end
    result_string
  end
end
