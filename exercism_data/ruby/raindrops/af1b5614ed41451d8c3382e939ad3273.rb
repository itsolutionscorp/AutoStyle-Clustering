class Raindrops

  def self.convert(number)
    string = form_string(number)
    string.empty? ? number.to_s : string
  end

  def self.form_string(number)
    string = ""
    string += "Pling" if number % 3 == 0
    string += "Plang" if number % 5 == 0
    string += "Plong" if number % 7 == 0
    return string
  end
end
