class Raindrops
  def convert(n)
    string = ''
    string << 'Pling' if n % 3 == 0
    string << 'Plang' if n % 5 == 0
    string << 'Plong' if n % 7 == 0
    return string unless string.empty?
    n.to_s
  end
end
