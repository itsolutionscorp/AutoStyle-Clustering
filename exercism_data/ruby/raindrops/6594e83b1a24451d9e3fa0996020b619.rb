class Raindrops
  def self.convert(number)
    string = ""
    string << "Pling"      if number % 3 == 0
    string << "Plang"      if number % 5 == 0
    string << "Plong"      if number % 7 == 0
    string << number.to_s  if string.empty?
    string
  end
end
