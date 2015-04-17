
#Hamming class
class Hamming
  def self.compute(cad1, cad2)
    dif = 0
    for i in 0..cad1.length - 1
      if cad2[i] && cad1[i] != cad2[i]
        dif = dif + 1
      end
    end
    dif		
  end
end	