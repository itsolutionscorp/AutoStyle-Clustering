class Raindrops
  def convert(n)
    (pl = [['Pling'][n%3], ['Plang'][n%5], ['Plong'][n%7]].compact.join).empty? ? n.to_s : pl
  end
end
