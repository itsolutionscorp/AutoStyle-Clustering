class Raindrops
  def convert(n)
    msg = ''
    ppp_hash = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    ppp_hash.each do |k, e|
      msg << e if n.divisible_by? k
    end
    msg == '' ? n.to_s : msg
  end
end
class Fixnum
  def divisible_by?(k)
    self % k == 0
  end
end
