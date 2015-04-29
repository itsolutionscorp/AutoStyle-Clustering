class Raindrops
  def self.convert(thing)
    string = ""
    thing % 3 == 0 ? string << "Pling" : false
    thing % 5 == 0 ? string << "Plang" : false
    thing % 7 == 0 ? string << "Plong" : false
    string.empty? ? "#{thing}" : string
  end
end
