class Raindrops

  def self.convert(number)
    status = []
    status << "Pling" if number % 3 == 0
    status << "Plang" if number % 5 == 0
    status << "Plong" if number % 7 == 0
    status.empty? ? number.to_s : status.join
  end

end
