module Raindrops

  def Raindrops.convert(val) 
    res = ""
    res << "Pling" if val % 3 == 0
    res << "Plang" if val % 5 == 0
    res << "Plong" if val % 7 == 0
    (res == "") ? val.to_s : res
  end
end
