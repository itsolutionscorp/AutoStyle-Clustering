class Raindrops
  def self.convert(int, array = [])
    array << "Pling" if int % 3 == 0
    array << "Plang" if int % 5 == 0
    array << "Plong" if int % 7 == 0
    if array.empty?
      int.to_s
    else
      array.join
    end
  end
end
