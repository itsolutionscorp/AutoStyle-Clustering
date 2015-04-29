class Raindrops
  def convert (n)
    res = ''
    if n % 3 == 0
      res = "#{res}Pling"
    end
    if n % 5 == 0
      res = "#{res}Plang"
    end
    if n % 7 == 0
      res = "#{res}Plong"
    end
    if res == ''
      res = n.to_s
    end
    res
  end
end
