class Raindrops

  def convert (num)
    res = ''
    if num % 3 == 0 then
      res += 'Pling'
    end
    if num % 5 == 0 then
      res += 'Plang'
    end
    if num % 7 == 0 then
      res += 'Plong'
    end
    if res == '' then
      res = num.to_s
    end
    res
  end

end
