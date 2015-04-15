class Raindrops
  def Raindrops.convert(num)
  out = ""
  out += "Pling"  if num%3 == 0
  out += "Plang"  if num%5 == 0
  out += "Plong"  if num%7 == 0
  if out=="" then
	  num.to_s
  else
	  out
  end
  end
end
