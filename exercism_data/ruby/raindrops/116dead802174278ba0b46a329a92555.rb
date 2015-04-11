class Raindrops

  def self.convert(x)
    if (x % 3 == 0 ) && (x % 5 == 0) && (x % 7 == 0)
      p 'PlingPlangPlong'
    elsif (x % 3 == 0) && (x % 5 == 0)
      p 'PlingPlang'
    elsif (x % 3 == 0) && (x % 7 == 0)
      p 'PlingPlong'
    elsif (x % 5 == 0) && (x % 7 == 0)
      p 'PlangPlong'
    elsif x % 3 == 0
      p 'Pling'
    elsif x % 5 == 0
      p 'Plang'
    elsif x % 7 == 0
      p 'Plong'
    else
      p x.to_s
    end
  end

end
