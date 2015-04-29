class Raindrops
  def self.convert(d)
    result = ""
    (d%3 == 0) ? result << "Pling" : result
    (d%5 == 0) ? result << "Plang" : result
    (d%7 == 0) ? result << "Plong" : result
    return result == "" ? d.to_s : result;
  end
end
