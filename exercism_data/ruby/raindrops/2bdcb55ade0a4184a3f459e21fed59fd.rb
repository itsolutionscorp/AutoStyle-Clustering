class Raindrops
  def self.convert number
    result_string = ""
    result_string += 'Pling' if number%3 == 0
    result_string += 'Plang' if number%5 == 0
    result_string += 'Plong' if number%7 == 0
    result_string = number.to_s if result_string.empty?
    result_string
  end
end
