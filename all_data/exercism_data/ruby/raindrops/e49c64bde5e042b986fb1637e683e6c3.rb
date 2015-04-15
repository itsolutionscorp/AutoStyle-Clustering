# class RainDrops
class Raindrops
  def convert(n)
    msg = ''
    { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }.each do |k, e|
      msg << e if n % k == 0
    end
    msg = n.to_s if msg == ''
    msg
  end
end
