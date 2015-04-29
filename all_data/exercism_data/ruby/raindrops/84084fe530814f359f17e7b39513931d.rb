class Raindrops
  def self.convert(num)
    return "1" if num == 1

    s = ""

    { 3 => "Pling", 5 => "Plang", 7 => "Plong" }.each do |key, val|
      if num % key == 0
        s << val
      end
    end

    if s == ""  
      num.to_s
    else
      s
    end
    
  end
end
