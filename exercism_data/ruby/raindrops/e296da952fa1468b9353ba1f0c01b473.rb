class Raindrops
  def self.convert num
    output = ''
    num % 3 == 0 ? output << "Pling" : nil
    num % 5 == 0 ? output << "Plang" : nil
    num % 7 == 0 ? output << "Plong" : nil
    output == '' ? output << num.to_s : output
    output
  end
end
