class Raindrops
  def self.convert(x)

  output = ""

  if x % 3 == 0 && x % 5 == 0 && x % 7 == 0
    output << 'PlingPlangPlong'
  elsif x % 3 == 0 && x % 5 == 0
    output << 'PlingPlang'
  elsif x % 3 == 0 && x % 7 == 0
    output << 'PlingPlong'
  elsif x % 5 == 0 && x % 7 == 0
    output << 'PlangPlong'
  elsif x % 3 == 0
    output << 'Pling'
  elsif x % 5 == 0
    output << 'Plang'
  elsif x % 7 == 0
    output << 'Plong'
  else
    output = x.to_s
  end

end

end
