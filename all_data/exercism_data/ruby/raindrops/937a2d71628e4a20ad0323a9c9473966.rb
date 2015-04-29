class Raindrops
  def self.convert(x)
    if    (x % 7 == 0) && (x % 3 == 0) && (x % 5 == 0)
      "PlingPlangPlong"
    elsif (x % 7 == 0) && (x % 3 == 0)
      "PlingPlong"
    elsif (x % 7 == 0) && (x % 5 != 0)
      "Plong"
    elsif (x % 3 == 0) && (x % 5 == 0)
      "PlingPlang"
    elsif (x % 7 == 0) && (x % 5 == 0) 
      "PlangPlong"
    elsif (x % 3 == 0) && (x % 5 != 0)
      "Pling"
    elsif (x % 5 == 0) 
      "Plang"
    else
      x.to_s
    end
  end
end


if __FILE__ == $0 

a = Raindrops.new
puts  Raindrops.convert(3)
end
