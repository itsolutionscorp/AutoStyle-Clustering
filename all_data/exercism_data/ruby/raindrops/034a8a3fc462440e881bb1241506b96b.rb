class Raindrops

  def self.convert(number)
    status = []
    number % 3 == 0 ? status[0] = "Pling" : status[0] = ""
    number % 5 == 0 ? status[1] = "Plang" : status[1] = ""
    number % 7 == 0 ? status[2] = "Plong" : status[2] = ""

    status.join == "" ? number.to_s : status.join
  end
end
