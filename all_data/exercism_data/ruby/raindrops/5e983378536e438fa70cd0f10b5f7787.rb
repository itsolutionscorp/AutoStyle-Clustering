class Raindrops

  def self.convert(value)
    case   
      when value % 3 == 0 && value % 5 == 0 && value % 7 == 0 then "PlingPlangPlong"
      when value % 3 == 0 && value % 5 == 0 then "PlingPlang"
      when value % 3 == 0 && value % 7 == 0 then "PlingPlong"
      when value % 5 == 0 && value % 7 == 0 then "PlangPlong"
      when value % 3 == 0 then "Pling"
      when value % 5 == 0 then "Plang"
      when value % 7 == 0 then "Plong"
      else value.to_s
    end  
  end
end
    
