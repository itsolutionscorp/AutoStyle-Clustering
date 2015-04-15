class Raindrops
  attr_reader :mystack

  def self.convert num
    mystack = []
    mystack << 'Pling' if num % 3 == 0
    mystack << 'Plang' if num % 5 == 0
    mystack << 'Plong' if num % 7 == 0
    mystack.any? ? mystack.join : "#{num}"
  end
end
