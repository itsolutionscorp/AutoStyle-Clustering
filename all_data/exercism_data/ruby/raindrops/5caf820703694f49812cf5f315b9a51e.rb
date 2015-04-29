class Raindrops
  def convert(n)
    [*['Pling'][n%3], *['Plang'][n%5], *['Plong'][n%7]].reduce(:+) || n.to_s
  end
end
