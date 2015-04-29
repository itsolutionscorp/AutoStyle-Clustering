class Raindrops
  def self.convert(drops)

    dropplets = ""
    [3, 5, 7].each do |x|
      if drops % x == 0
        case x
          when 3
            dropplets << "Pling"
          when 5
            dropplets << "Plang"
          when 7
            dropplets << "Plong"
        end
      end
    end
    if dropplets == ""
      dropplets = drops.to_s
    end
    dropplets
  end
end
