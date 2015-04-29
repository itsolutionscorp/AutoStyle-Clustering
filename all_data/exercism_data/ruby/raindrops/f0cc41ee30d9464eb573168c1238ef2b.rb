#Raindrops class
class Raindrops
  def convert(num)
    regresa = ""
      if num % 3 == 0
        regresa = regresa + 'Pling'
      end
      if num % 5 == 0
        regresa = regresa + 'Plang'
      end
      if num % 7 == 0
        regresa = regresa + 'Plong'
      end
      if regresa != ''
        regresa
      else
        num.to_s
      end
    end
end
