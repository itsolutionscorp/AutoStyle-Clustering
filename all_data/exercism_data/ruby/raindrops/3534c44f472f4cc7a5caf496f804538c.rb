require 'prime'
class Raindrops
  def self.convert(num)
    output = ""
    fctr = Prime.prime_division(num).flatten

    output << 'Pling' if fctr.include?(3)
    output << 'Plang' if fctr.include?(5)
    output << 'Plong' if fctr.include?(7)

    fctr.include?(3) || fctr.include?(5) || fctr.include?(7) ? output : num.to_s

    #if fctr.include?(3)
      #output << 'Pling'
      #output << 'Plang' if fctr.include?(5)
      #output << 'Plong' if fctr.include?(7)
      #output
    #elsif fctr.include?(5)
      #output << 'Plang'
      #output << 'Plong' if fctr.include?(7)
      #output
    #elsif fctr.include?(7)
      #output << 'Plong'
      #output
    #else
      #return num.to_s
    #end
  end
end
