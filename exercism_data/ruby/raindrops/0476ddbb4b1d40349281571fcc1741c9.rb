class Raindrops
  def self.convert(num)
    if num%105 == 0
      'PlingPlangPlong'
    elsif num%15 == 0
      'PlingPlang'
    elsif num%21 == 0
      'PlingPlong'
    elsif num%35 == 0
      'PlangPlong'
    elsif num%3 == 0
      'Pling'
    elsif num%5 == 0
      'Plang'
    elsif num%7 == 0
      'Plong'
    else
      num.to_s
    end
  end
end
