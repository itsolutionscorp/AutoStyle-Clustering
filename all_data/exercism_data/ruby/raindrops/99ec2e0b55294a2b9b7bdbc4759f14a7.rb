class Raindrops
  def self.convert(x)
    message = ""
    if x % 3 == 0 then message << "Pling" end
    if x % 5 == 0 then message << "Plang" end
    if x % 7 == 0 then message << "Plong" end
    if message.empty? then "#{x}" else message end
  end
  
end
