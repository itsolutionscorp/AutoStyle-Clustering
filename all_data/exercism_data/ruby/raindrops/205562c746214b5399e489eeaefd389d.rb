# exercism - ruby/raindrops
class Raindrops
  def self.convert(n)
    result = plinger(n) + planger(n) + plonger(n)
    result != '' ? result : "#{n}"
  end

  @private
  def self.plinger(n) n % 3 == 0 ? 'Pling' : '' end
  def self.planger(n) n % 5 == 0 ? 'Plang' : '' end
  def self.plonger(n) n % 7 == 0 ? 'Plong' : '' end
end
