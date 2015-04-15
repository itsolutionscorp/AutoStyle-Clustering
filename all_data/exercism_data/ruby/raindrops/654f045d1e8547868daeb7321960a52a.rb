class Raindrops
  def self.convert (number)
    txt=""
    txt="Pling" if number.remainder(3)==0
    txt+="Plang" if number.remainder(5)==0
    txt+="Plong" if number.remainder(7)==0
    txt=number.to_s if txt.empty?
    return txt
  end
end
