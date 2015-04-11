class Raindrops
  def convert(num)
    out = ""
    justnumb = true
    [3,5,7].each do |elem|
      justnumb = false if num % elem == 0
    end
    out = num.to_s if justnumb == true
    out = out + 'Pling' if num % 3 == 0
    out = out + 'Plang' if num % 5 == 0
    out = out + 'Plong' if num % 7 == 0
    out
  end
end
