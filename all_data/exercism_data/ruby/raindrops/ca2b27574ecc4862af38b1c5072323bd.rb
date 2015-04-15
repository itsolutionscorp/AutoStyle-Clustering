class Raindrops
  def  self.convert (n)
      a= ""
      a << "1" if n == 1
      a << "Pling" if n%3 == 0
      a << "Plang" if n%5 == 0
      a << "Plong" if n%7 == 0
      a << n.to_s if (n%3 != 0) && (n%5 != 0) && (n%7 != 0)  && (n != 1)
      a
  end
end
